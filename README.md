# Sort Save

Project that saves data from a text file to a Postgres database as soon as possible.

## Getting Started

This are instructions on how to start the application.

### Prerequisites

```
Docker
```

## Running

```
docker-compose up -d
docker logs sortsave_app_1
```

## Connecting to Postgres db

Port 5432 is exposed. So just connect to localhost:5432 with you favourite psql client.

## Stopping

```
docker-compose down
```

## Authors

* **Vojko Drev** - *Initial work* - (vojkodrev@gmail.com)
