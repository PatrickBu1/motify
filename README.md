Docker setup:
`docker run --name motify -e POSTGRES_PASSWORD=root -p 5432:5432 -v path-to-motify/data:/var/lib/postgresql/data -d postgres`
 Donfigure shared paths from `Docker -> Preferences... -> Resources -> File Sharing`.

 
