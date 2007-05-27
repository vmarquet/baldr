;NSIS Baldr Installer
;Written by NeZetiC

!ifndef VERSION
  !define VERSION '0.1a'
!endif

;--------------------------------
;Include Modern UI

  !include "MUI.nsh"

;--------------------------------

;General

  ;Name and file
  Name "Baldr ${VERSION}"
  OutFile "Baldr-${VERSION}-setup.exe"

  ;Default installation folder
  InstallDir "$PROGRAMFILES\Baldr"
  
  ;Get installation folder from registry if available
  InstallDirRegKey HKCU "Software\Baldr" ""
  
  RequestExecutionLevel admin

;--------------------------------
;Interface Settings

  !define MUI_ABORTWARNING
  !define MUI_WELCOMEFINISHPAGE_BITMAP "baldr.bmp"

;--------------------------------
;Pages
  !define MUI_WELCOMEPAGE_TEXT "\r\n\r\n$_CLICK"
    
  !insertmacro MUI_PAGE_WELCOME
  !insertmacro MUI_PAGE_LICENSE "License.txt"
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_INSTFILES
  
  !define MUI_FINISHPAGE_LINK "Visiter le site d'esiea-labs"
  !define MUI_FINISHPAGE_LINK_LOCATION "http://labs.esiea.fr/"

  !define MUI_FINISHPAGE_RUN "$INSTDIR\start.bat"
  !define MUI_FINISHPAGE_NOREBOOTSUPPORT
  !insertmacro MUI_PAGE_FINISH
  
  !insertmacro MUI_UNPAGE_CONFIRM
  !insertmacro MUI_UNPAGE_INSTFILES

;--------------------------------
;Languages
 
  !insertmacro MUI_LANGUAGE "French"

;--------------------------------
;Installer Sections

Section "Baldr" SecBaldr

  SectionIn RO
  
  SetOutPath "$INSTDIR\Images"
  File "bin\Images\"
  
  SetOutPath "$INSTDIR\lib"
  File "bin\lib\"
  
  SetOutPath "$INSTDIR"
  File "bin\"
  
  ;Store installation folder
  WriteRegStr HKCU "Software\Baldr" "" $INSTDIR
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Baldr" "DisplayName" "Baldr v${VERSION}"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Baldr" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Baldr" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Baldr" "NoRepair" 1
  
  ;Create uninstaller
  WriteUninstaller "$INSTDIR\Uninstall.exe"

SectionEnd

; Shortcuts
Section "Raccourcis" secShort

  CreateDirectory "$SMPROGRAMS\Baldr"
  CreateShortCut "$SMPROGRAMS\Baldr\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0
  CreateShortCut "$SMPROGRAMS\Baldr\Baldr.lnk" "$INSTDIR\start.bat" "" "$INSTDIR\baldr.ico" 0
  
SectionEnd

;--------------------------------
;Descriptions

  ;Language strings
  LangString DESC_SecBaldr ${LANG_FRENCH} "Baldr version ${VERSION}"
  LangString DESC_SecShort ${LANG_FRENCH} "Ajoute les raccourcis de Baldr dans le menu Démarrer de Windows"

  ;Assign language strings to sections
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${SecBaldr} $(DESC_SecBaldr)
    !insertmacro MUI_DESCRIPTION_TEXT ${SecShort} $(DESC_SecShort)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END

;--------------------------------
;Uninstaller Section

Section "Uninstall"

  DeleteRegKey /ifempty HKCU "Software\Baldr"
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Baldr"

  Delete "$INSTDIR\Uninstall.exe"
  Delete "$INSTDIR\Images\*.*"
  Delete "$INSTDIR\lib\*.*"
  Delete "$INSTDIR\baldr.ico"
  Delete "$INSTDIR\Balder.jar"
  Delete "$INSTDIR\start.bat"
  
  RMDir "$INSTDIR\Images"
  RMDir "$INSTDIR\lib"
  RMDir "$INSTDIR"
  
  ; Remove shortcuts
  Delete "$SMPROGRAMS\Baldr\*.*"
  RMDir "$SMPROGRAMS\Baldr"

SectionEnd
