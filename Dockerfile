FROM openjdk
COPY target/YnetBreakingNews-0.0.1-SNAPSHOT.jar .
CMD ["java" , "-jar" , "YnetBreakingNews-0.0.1-SNAPSHOT.jar" ]