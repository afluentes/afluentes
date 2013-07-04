del /s *.classpath
del /s *.iml
del /s *.project
del /s Thumbs.db

for /d /r . %%d in (.idea) do @if exist "%%d" rd /s /q "%%d"
for /d /r . %%d in (.settings) do @if exist "%%d" rd /s /q "%%d"
for /d /r . %%d in (target) do @if exist "%%d" rd /s /q "%%d"

@rem pause