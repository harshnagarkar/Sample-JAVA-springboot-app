


if (-not (Test-Path -Path "$HOME/docker/volumes/postgres"))
{
    mkdir -p $HOME/docker/volumes/postgres
}
if (Test-Path -Path "$HOME/docker/volumes/postgres/data"){
    rm  ($HOME+'/docker/volumes/postgres/data')
}

# docker run --rm --name pg-docker  -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql postgres
# docker run --name pg-docker -e POSTGRES_PASSWORD=post1234 -d -p 5432:5432 postgres:alpine
docker run --rm --name pg-docker -v ($HOME+"/docker/volumes/postgres:/var/lib/postgresql") -e POSTGRES_PASSWORD=root -e POSTGRES_DB="dev" -d -p 5432:5432  postgres
$Env:PGPASSWORD = 'root'
sleep 5
echo "reached here"
psql -U postgres -d dev  -h localhost -f 'schema.sql' 
psql -U postgres -d dev   -h localhost -f 'data.sql'
