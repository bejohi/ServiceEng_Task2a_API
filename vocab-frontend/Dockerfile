# build stage
FROM node:lts-alpine as frontend
WORKDIR /app
RUN npm install -g parcel-bundler
COPY package*.json ./
COPY index.html index.html
COPY src/ src/
RUN npm install && parcel build index.html

FROM  golang:latest AS backend
ENV GO111MODULE=on
WORKDIR /app
COPY go.mod go.mod
COPY server.go main.go
RUN CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o server .

FROM scratch 
COPY --from=frontend /app/dist /dist
COPY --from=backend /app/server /
WORKDIR /
ENTRYPOINT ["/server"]