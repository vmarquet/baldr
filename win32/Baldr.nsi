;NSIS Baldr Installer
;Written by NeZetiC

!ifndef VERSION
  !define VERSION '0.2b'
!endif

;--------------------------------
;Include

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

  !define MUI_FINISHPAGE_RUN "$INSTDIR\baldr.exe"
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
  CreateShortCut "$SMPROGRAMS\Baldr\Documentation.lnk" "$INSTDIR\baldr.chm" "" "$INSTDIR\baldr.chm" 0
  CreateShortCut "$SMPROGRAMS\Baldr\Baldr.lnk" "$INSTDIR\baldr.exe" "" "$INSTDIR\baldr.ico" 0
  
SectionEnd

; Extensions
Section "Extensions" secExt

    ; back up old value of .baldr
!define Index "Line${__LINE__}"
  ReadRegStr $1 HKCR ".baldr" ""
  StrCmp $1 "" "${Index}-NoBackup"
    StrCmp $1 "Baldr.baldr" "${Index}-NoBackup"
    WriteRegStr HKCR ".baldr" "backup_val" $1
"${Index}-NoBackup:"
  WriteRegStr HKCR ".baldr" "" "Baldr.baldr"
  ReadRegStr $0 HKCR "Baldr.baldr" ""
  StrCmp $0 "" 0 "${Index}-Skip"
	WriteRegStr HKCR "Baldr.baldr" "" "Analyse Baldr"
	WriteRegStr HKCR "Baldr.baldr\shell" "" "open"
	WriteRegStr HKCR "Baldr.baldr\DefaultIcon" "" "$INSTDIR\baldr.ico,0"
"${Index}-Skip:"
  WriteRegStr HKCR "Baldr.baldr\shell\open\command" "" \
    '$INSTDIR\baldr.exe "%1"'
  WriteRegStr HKCR "Baldr.baldr\shell\edit" "" "Editer l'analyse"
  WriteRegStr HKCR "Baldr.baldr\shell\edit\command" "" \
    '$INSTDIR\baldr.exe "%1"'
 
  System::Call 'Shell32::SHChangeNotify(i 0x8000000, i 0, i 0, i 0)'
!undef Index
  ; Rest of script
  
    ; back up old value of .baldrx
!define Index "Line${__LINE__}"
  ReadRegStr $1 HKCR ".baldrx" ""
  StrCmp $1 "" "${Index}-NoBackup"
    StrCmp $1 "Baldr.baldrx" "${Index}-NoBackup"
    WriteRegStr HKCR ".baldrx" "backup_val" $1
"${Index}-NoBackup:"
  WriteRegStr HKCR ".baldrx" "" "Baldr.baldrx"
  ReadRegStr $0 HKCR "Baldr.baldrx" ""
  StrCmp $0 "" 0 "${Index}-Skip"
	WriteRegStr HKCR "Baldr.baldrx" "" "Analyse Baldr (Compressé)"
	WriteRegStr HKCR "Baldr.baldrx\shell" "" "open"
	WriteRegStr HKCR "Baldr.baldrx\DefaultIcon" "" "$INSTDIR\baldr.ico,0"
"${Index}-Skip:"
  WriteRegStr HKCR "Baldr.baldrx\shell\open\command" "" \
    '$INSTDIR\baldr.exe "%1"'
  WriteRegStr HKCR "Baldr.baldrx\shell\edit" "" "Editer l'analyse"
  WriteRegStr HKCR "Baldr.baldrx\shell\edit\command" "" \
    '$INSTDIR\baldr.exe "%1"'
 
  System::Call 'Shell32::SHChangeNotify(i 0x8000000, i 0, i 0, i 0)'
!undef Index
  ; Rest of script  
  
SectionEnd

;--------------------------------
;Descriptions

  ;Language strings
  LangString DESC_SecBaldr ${LANG_FRENCH} "Baldr version ${VERSION}"
  LangString DESC_SecShort ${LANG_FRENCH} "Ajoute les raccourcis de Baldr dans le menu Démarrer de Windows"
  LangString DESC_SecExt ${LANG_FRENCH} "Associe les extentions .baldr et .baldrx à Baldr"

  ;Assign language strings to sections
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${SecBaldr} $(DESC_SecBaldr)
    !insertmacro MUI_DESCRIPTION_TEXT ${SecShort} $(DESC_SecShort)
    !insertmacro MUI_DESCRIPTION_TEXT ${SecExt} $(DESC_SecExt)
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
  Delete "$INSTDIR\baldr.exe"
  Delete "$INSTDIR\baldr.chm"
  Delete "$INSTDIR\GPL.txt"
  Delete "$INSTDIR\License.txt"
  
  RMDir "$INSTDIR\Images"
  RMDir "$INSTDIR\lib"
  RMDir "$INSTDIR"
  
  ; Remove shortcuts
  Delete "$SMPROGRAMS\Baldr\*.*"
  RMDir "$SMPROGRAMS\Baldr"
  
  ; Remove Exts
    ;start of restore script
!define Index "Line${__LINE__}"
  ReadRegStr $1 HKCR ".baldr" ""
  StrCmp $1 "Baldr.baldr" 0 "${Index}-NoOwn" ; only do this if we own it
    ReadRegStr $1 HKCR ".baldr" "backup_val"
    StrCmp $1 "" 0 "${Index}-Restore" ; if backup="" then delete the whole key
      DeleteRegKey HKCR ".baldr"
    Goto "${Index}-NoOwn"
"${Index}-Restore:"
      WriteRegStr HKCR ".baldr" "" $1
      DeleteRegValue HKCR ".baldr" "backup_val"
   
    DeleteRegKey HKCR "Baldr.baldr" ;Delete key with association settings
 
    System::Call 'Shell32::SHChangeNotify(i 0x8000000, i 0, i 0, i 0)'
"${Index}-NoOwn:"
!undef Index
  ;rest of script

;start of restore script
!define Index "Line${__LINE__}"
  ReadRegStr $1 HKCR ".baldrx" ""
  StrCmp $1 "Baldr.baldrx" 0 "${Index}-NoOwn" ; only do this if we own it
    ReadRegStr $1 HKCR ".baldrx" "backup_val"
    StrCmp $1 "" 0 "${Index}-Restore" ; if backup="" then delete the whole key
      DeleteRegKey HKCR ".baldrx"
    Goto "${Index}-NoOwn"
"${Index}-Restore:"
      WriteRegStr HKCR ".baldrx" "" $1
      DeleteRegValue HKCR ".baldrx" "backup_val"
   
    DeleteRegKey HKCR "Baldr.baldrx" ;Delete key with association settings
 
    System::Call 'Shell32::SHChangeNotify(i 0x8000000, i 0, i 0, i 0)'
"${Index}-NoOwn:"
!undef Index
  ;rest of script

SectionEnd
