@echo off
if "%1"=="" (
    echo Veuillez passer un paramètre.
    exit /b 1
)

cd ..\ScriptFront
python retrieve_notifications.py %1