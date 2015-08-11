@echo off
rem Created by philipborg
title Create IDE Project Files
echo This requires Java Development Kit 8 and Android SDK level 22
pause
call gradlew --no-daemon clean eclipse afterEclipseImport
echo If you used this to update your project files the assets may be double linked, simply remove one
pause