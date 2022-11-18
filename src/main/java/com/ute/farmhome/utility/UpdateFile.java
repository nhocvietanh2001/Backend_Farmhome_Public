package com.ute.farmhome.utility;

import com.ute.farmhome.dto.FileUpload;

public interface UpdateFile {
    void update(FileUpload fileUpload);

    void delete(String fullPath);
}
