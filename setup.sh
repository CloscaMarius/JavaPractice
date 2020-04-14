#!/bin/bash
#-------------------------------------------------------------------------------
# Script which completes the setup of this project (cloning extra repos)
#-------------------------------------------------------------------------------

#trying to autodetect location of other repos based on current git url:
REPO="$(git remote get-url origin)"
BASE=`echo $REPO | awk -F/java/ '{print $1}'`/java
REST=`echo $REPO | awk -F/java/ '{print $2}'`
CLASS_NAME=`echo $REST | awk -F/ '{print $1}'`
echo "Origin: $REPO -> base: '$BASE', class: '$CLASS_NAME'"

#If auto-detection above didn't work, may need to manuall set these:
#1) Choose the common base prefixe (git@ if you have ssh key configured, https otherwise)
#BASE=git@gitlab.com:wantsome/java/java-basic
#BASE=https://gitlab.com/wantsome/java/java-basic
#2) Set the name of the specific class (like 'seria7', 'seria8')
#CLASS_NAME=seria8

#-------------------------------------------------------------------------------

if [[ $CLASS_NAME != *"seria"* ]]; then
  echo ''
  echo "ERROR: class name '$CLASS_NAME' doesn't start with 'seria..'! Possibly wrong autodetection, cannot continue!"
  exit 1
fi

ACT=$1
if [ "$ACT" = "clean-all" ]; then
  echo '---- Full cleanup - deleting dirs before clone... ----'
  rm -rf curs
  rm -rf clasa
  echo ''
fi

echo '---- Cloning course repos... ----'
if [ ! -d "curs" ]; then
  git clone ${BASE}/common/curs.git
else
  echo 'curs repo: already cloned'
fi

if [ ! -e curs/.git/hooks/pre-commit ]; then
  echo 'curs repo: adding pre-commit hook...'
  cp gradle/pre-commit curs/.git/hooks
else
  echo 'curs repo: pre-commit hook ok'
fi

if [ ! -d "clasa" ]; then
  git clone ${BASE}/${CLASS_NAME}/clasa.git
else
  echo 'clasa repo: already cloned'
fi

if [ ! -e clasa/.git/hooks/pre-commit ]; then
  echo 'clasa repo: adding pre-commit hook...'
  cp gradle/pre-commit clasa/.git/hooks
else
  echo 'clasa repo: pre-commit hook ok'
fi

BOOK_URL="https://profs.info.uaic.ro/~acf/java/Cristian_Frasinaru-Curs_practic_de_Java.pdf"
BOOK="curs/resurse_diverse/books/Cristian_Frasinaru-Curs_practic_de_Java.pdf"
if [ ! -e $BOOK ]; then
  echo ''
  echo '---- Downloading books... ----'
  wget -O $BOOK $BOOK_URL
  retVal=$?
  if [ $retVal -ne 0 ]; then
    echo "Error running wget, trying powershell (maybe we are on Windows?)..."
    powershell -Command "(new-object System.Net.WebClient).DownloadFile('$BOOK_URL','$BOOK')"
  fi
fi

if [ "$ACT" = "clean" ] || [ "$ACT" = "clean-all" ]; then
  echo ''
  echo '----- Cleaning up temp files, cache... ----'
  rm -rf build
  rm -rf .gradle
  rm -rf .idea
  rm -rf out
  ./gradlew clean
fi

echo 'All done!'

echo '------------------------------------------------------------------------'
echo 'JAVA version:'
java -version
echo 'JAVA COMPILER version (if JDK installed):'
javac -version
echo '------------------------------------------------------------------------'
git --version
echo 'Git url of current project:'
git remote get-url origin
echo 'Checking GitLab access by SSH key...'
ssh -T git@gitlab.com
echo '------------------------------------------------------------------------'

if [ "$ACT" = "clean" ] || [ "$ACT" = "clean-all" ]; then
  echo 'RECOMMENDED: if you are now in IntelliJ, you should also:'
  echo ' - first: run "Update project" (from Git) - Ctrl+T'
  echo ' - then:  do a clean restart - from menu: File \ Invalidate Caches/Restart... -> Invalidate and Restart'
  echo '          or at least: do a Gradle refresh - from Gradle tab (top-right side), 1st button: "Reimport all gradle projects"'
  echo '------------------------------------------------------------------------'
else
  echo You may also try the cleanup options:
  echo $0 'clean     - cleans just the caches and temp files'
  echo $0 'clean-all - DELETES all extra repos and clones them again'
  echo '------------------------------------------------------------------------'
fi
