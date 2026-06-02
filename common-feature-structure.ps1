param(
    [string]$Feature
)

$BasePath = "src/main/java/br/com/bookschange/api/application/$Feature"

$Folders = @(
    "adapters/in/dtos/request",
    "adapters/in/dtos/response",
    "adapters/out/repositories",
    "mappers",
    "ports/in",
    "ports/out",
    "usecases"
)

foreach ($folder in $Folders) {
    New-Item -ItemType Directory -Force -Path "$BasePath/$folder" | Out-Null
}

Write-Host "Estrutura criada para a feature: $Feature"

# How to use: .\common-feature-structure.ps1 <domain>
# Ex.: .\common-feature-structure.ps1 user