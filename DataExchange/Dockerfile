FROM python:3.13-slim
WORKDIR /app

RUN pip install poetry

COPY . /app

# Installer les dépendances
RUN pip install psycopg2-binary
RUN poetry install

# Commande par défaut pour exécuter le container
CMD ["poetry", "run", "python", "main.py"]
