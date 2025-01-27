@echo off

if "%1"=="" (
    echo Veuillez passer un paramètre.
    exit /b 1
)

cd ..\ScriptFront
python send_configurations.py %1