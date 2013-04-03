# 기본 환경설정
1. Maven, Eclipse, Android SDK 설치
2. 환경변수에 "ANDROID_HOME"이라는 변수명으로 Andoid SDK의 홈을 지정 (pom.xml과 Robolectric에서 참조함)

# Eclipse 환경 구성
1. mvn eclipse:eclipse 로 Eclipse 파일 생성
2. m2e 설치
	- updatesite : http://download.eclipse.org/technology/m2e/release
3. m2e-android plugin 설치
	- updat site : http://rgladwell.github.com/m2e-android/updates/

4. ADT 설정은 .project 파일을 직접 수정

# android-annotions를 위한 Eclipse
1. m2e-apt plugin 설치
	- updat site : http://download.jboss.org/jbosstools/updates/m2e-extensions/m2e-apt
2. m2e-apt 활성화
	- 'Project > Properties > Maven > Annotation Processing' 메뉴에서 'Enable project specific settings' 선택
	- 'Automatically configure JDT APT' 선택
4. Facctory Path에 Android-annotation 관련 파일이 있는지 확인
	- 'Project > Properties > Java compiler > Annotation Processing > Factroy Path' 
	- https://github.com/excilys/androidannotations/wiki/Building-Project-Maven-Eclipse 참조

# Build와 설치, 실행
	mvn package android:deploy android:run -Dmaven.test.skip=true
	
# 특이사항
- CalendarContract.Events.CONTENT_URI 를 통해 Calendar 등록을 사용하기 위해  Min SDK versionm을 14를 지정
- Min SDK version이 14이기 때문에 android.support.v4패캐지의 Fragment를 사용하지 않아도 되었으나, Roboguice에서는 SDK level과 관계없이 v4패키지를 사용해야하므로 동등한 비교를 위해 사용
- 위와 같은 이유로 support-v4-r7.jar파일도 depedency에 포함시킴
