@echo off
rem Created by philipborg
title Create IDE Project Files
echo This requires Java Development Kit 8 and Android 22
echo e for ECLIPSE and i for IDEA
set /p choice="Create project files for: "
if %choice% == e call gradlew eclipse
if %choice% == i call gradlew idea
pause