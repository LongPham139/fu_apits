package com.apits.apitssystembackend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;

@SpringBootApplication
public class ApitsSystemBackendApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ApitsSystemBackendApplication.class, args);

//        ClassLoader classLoader = ApitsSystemBackendApplication.class
//                .getClassLoader();
//
//        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
//        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//        if (FirebaseApp.getApps().isEmpty()) {
//            FirebaseApp.initializeApp(options);
//        } else {
//            FirebaseApp.getInstance();
//        }

    }

}
