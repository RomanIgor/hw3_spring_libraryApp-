FROM postgres:13.2-alpine
ENV POSTGRES_DB mylibrary
ENV POSTGRES_USER ${DB_USERNAME}
ENV POSTGRES_PASSWORD ${DB_PASSWORD}