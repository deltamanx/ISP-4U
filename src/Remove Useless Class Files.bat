@ECHO OFF
GOTO start

:clear
ECHO This will now remove all .class files.
PAUSE
ERASE *.class
ECHO Done!
GOTO end

:nevermind
ECHO Now exiting program.
GOTO end

:error
ECHO Invlaid input. Please use Y or N.
PAUSE
GOTO input

:start
ECHO This will remove all .class files from the current directory.
ECHO Is this OK? 

:input
SET /P M=Type (Y/N): 

IF %M%==Y GOTO clear
IF %M%==y GOTO clear
IF %M%==N GOTO nevermind
IF %M%==n GOTO nevermind
GOTO error

:end
PAUSE