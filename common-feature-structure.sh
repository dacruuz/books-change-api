# This is used for Linux or MAC users

#!/bin/bash

FEATURE=$1
BASE_PATH="src/main/java/br/com/bookschange/api/application"

mkdir -p "$BASE_PATH/$FEATURE/adapters/in/dtos/request"
mkdir -p "$BASE_PATH/$FEATURE/adapters/in/dtos/response"
mkdir -p "$BASE_PATH/$FEATURE/adapters/out/repositories"
mkdir -p "$BASE_PATH/$FEATURE/mappers"
mkdir -p "$BASE_PATH/$FEATURE/ports/in"
mkdir -p "$BASE_PATH/$FEATURE/ports/out"
mkdir -p "$BASE_PATH/$FEATURE/usecases"

echo "Estrutura criada para a feature: $FEATURE"

# How to use: .\common-feature-structure.ps1 <domain>
# Ex.: .\common-feature-structure.ps1 user