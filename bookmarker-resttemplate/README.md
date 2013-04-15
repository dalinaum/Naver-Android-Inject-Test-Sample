# 기본 환경설정
1. Maven, Eclipse, Android SDK 설치
2. 환경변수에 "ANDROID_HOME"이라는 변수명으로 Andoid SDK의 홈을 지정 (pom.xml과 Robolectric에서 참조함)

# Eclipse 환경 구성
1. m2e 설치
	- updatesite : http://download.eclipse.org/technology/m2e/release
2. m2e-android plugin 설치
	- http://rgladwell.github.com/m2e-android/updates/

# Build와 설치, 실행
	mvn package android:deploy android:run -Dmaven.test.skip=true
	
# 특이사항
ROME 라이브러리가 용량이나 성능면에서 안드로이드에 적합한지는 의문이나, 예제의 간결함을 위해서 사용함
