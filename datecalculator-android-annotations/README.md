# Build와 설치, 실행
	mvn package android:deploy android:run -Dmaven.test.skip.true
	
# 특이사항
- CalendarContract.Events.CONTENT_URI 를 통해 Calendar 등록을 사용하기 위해  Min SDK version이 14를 지정
- Min SDK version이 14이기 때문에 android.support.v4패캐지의 Fragment를 사용하지 않아도 되었으나, Roboguice에서는 SDK level과 관계없이 v4패키지를 사용해야하므로 동등한 비교를 위해 사용
- 위와 같은 이유로 support-v4-r7.jar파일도 depedency에 포함시킴
