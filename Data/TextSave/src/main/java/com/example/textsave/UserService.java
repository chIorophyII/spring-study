package com.example.textsave;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void saveText(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file.getOriginalFilename()));
        String str;
        while ((str = reader.readLine()) != null) {
            String[] info = str.split(", "); // []

            User user = User.builder()
                    .name(info[0])
                    .age(Integer.parseInt(info[1]))
                    .gender(User.Gender.valueOf(info[2]))
                    .phone(info[3])
                    .region(info[4])
                    .build();

            userRepository.save(user);
        }

        reader.close();
    }
}
