Docker setup:
`docker run --name motify -e POSTGRES_PASSWORD=root -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres`