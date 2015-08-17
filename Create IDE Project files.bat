@echo off
rem Created by philipborg
title Create IDE Project Files
echo This requires Android SDK level 22 and at least JDK 8u40
pause
call gradlew --no-daemon clean eclipse afterEclipseImport
echo If you used this to update your project files the assets may be double linked, simply remove one
pause