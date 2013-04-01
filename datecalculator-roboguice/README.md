# Build와 설치, 실행
	mvn package android:deploy android:run -Dmaven.test.skip.true
	
# 특이사항
- CalendarContract.Events.CONTENT_URI 를 통해 Calendar 등록을 사용하기 위해  Min SDK version을 14로 지정
- Roboguice에서는 SDK level과 관계없이 v4패캐지를 사용해야하므로 support-v4-r7.jar파일도 depedency에 포함시킴
