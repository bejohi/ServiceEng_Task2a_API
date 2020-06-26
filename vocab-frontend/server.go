package main

import (
	"bytes"
	"fmt"
	"github.com/gin-gonic/gin"
	"io/ioutil"
	"net/http"
	"os"
	"strings"
)

var host string
var protocol string

func main() {
	host = os.Getenv("HOST")
	protocol = os.Getenv("PROTOCOL")
	if host == "" {
		os.Exit(4)
	}
	if protocol == "" {
		protocol = "http"
	}
	
	r := gin.Default()
	r.Use(handler)

	files, err := ioutil.ReadDir("/dist")
	if err != nil {
		os.Exit(5)
	}
	for _, file := range files {
		r.StaticFile(strings.TrimSuffix(file.Name(), "index.html"), "/dist/" + file.Name())
	}

	// Fall back to last index.html if not found
	r.Use(func(c *gin.Context) {
		path := strings.Trim(c.Request.URL.Path, "/")
		segments := strings.SplitAfter(path, "/")
		if path == "" {
			c.Next()
			return
		}
		c.Request.URL.Path = "/" + strings.Join(segments[:len(segments)-1], "")
		r.HandleContext(c)
	})

	r.Run(":80")
}

func handler(c *gin.Context) {
	if strings.HasPrefix(c.Request.URL.Path, "/api") {
		body, err := ioutil.ReadAll(c.Request.Body)
		if err != nil {
			http.Error(c.Writer, err.Error(), http.StatusInternalServerError)
			return
		}
		c.Request.Body = ioutil.NopCloser(bytes.NewReader(body))
		url := fmt.Sprintf("%s://%s%s", protocol, host, strings.TrimPrefix(c.Request.RequestURI, "/api"))
		fmt.Println(url)
		fmt.Println(c.Request.RequestURI)
		proxyReq, err := http.NewRequest(c.Request.Method, url, bytes.NewReader(body))

		proxyReq.Header = make(http.Header)
		for h, val := range c.Request.Header {
			proxyReq.Header[h] = val
		}

		client := &http.Client{}
		resp, err := client.Do(proxyReq)
		if err != nil {
			http.Error(c.Writer, err.Error(), http.StatusBadGateway)
			return
		}

		c.Status(resp.StatusCode)
		for h, val := range resp.Header {
			c.Writer.Header()[h] = val
		}

		defer resp.Body.Close()
		bodyContent, _ := ioutil.ReadAll(resp.Body)
		c.Writer.Write(bodyContent)

		c.Abort()
		return
	}
	c.Next()
}