# 기본 환경설정
1. Maven, Eclipse, Android SDK 설치
2. 환경변수에 "ANDROID_HOME"이라는 변수명으로 Andoid SDK의 홈을 지정 (pom.xml과 Robolectric에서 참조함)

# Eclipse 환경 구성
1. mvn eclipse:eclipse 로 Eclipse 파일 생성
2. m2e 설치
	- updatesite : http://download.eclipse.org/technology/m2e/release
3. m2e-android plugin 설치
	- http://rgladwell.github.com/m2e-android/updates/
4. ADT 설정은 .project 파일을 직접 수정

# Build와 설치, 실행
	mvn package android:deploy android:run -Dmaven.test.skip.true
	
# 특이사항
- CalendarContract.Events.CONTENT_URI 를 통해 Calendar 등록을 사용하기 위해  Min SDK version을 14로 지정
- Min SDK version이 14이기 때문에 android.support.v4패캐지의 Fragment를 사용하지 않아도 되었으나, Roboguice에서는 SDK level과 관계없이 v4패캐지를 사용해야하므로 동등한 비교를 위해 사용.
- 위와 같은 이유로 support-v4-r7.jar파일도 depedency에 포함시킴
