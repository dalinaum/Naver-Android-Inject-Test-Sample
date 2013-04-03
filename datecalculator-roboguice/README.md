# 기본 환경설정
1. Maven, Eclipse, Android SDK 설치
2. 환경변수에 "ANDROID_HOME"이라는 변수명으로 Andoid SDK의 홈을 지정 (pom.xml과 Robolectric에서 참조함)

# Eclipse 환경 구성
1. mvn eclipse:eclipse 로 Eclipse 파일 생성
2. m2e 설치
	- updatesite : http://download.eclipse.org/technology/m2e/release
3. m2e-android plugin 설치
	- updat site : http://rgladwell.github.com/m2e-android/updates/

# Build와 설치, 실행
	mvn package android:deploy android:run -Dmaven.test.skip=true
	
# 특이사항
- CalendarContract.Events.CONTENT_URI 를 통해 Calendar 등록을 사용하기 위해  Min SDK version을 14로 지정
- Roboguice에서는 SDK level과 관계없이 v4패캐지를 사용해야하므로 support-v4-r7.jar파일도 depedency에 포함시킴
