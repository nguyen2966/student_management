# Lab 1: Khởi Tạo & Kiến Trúc Hệ Thống


**Dùng dòng lệnh (Terminal)**
Tại thư mục gốc của project, chạy lệnh:
```bash
./mvnw spring-boot:run
```

## Bài tập 

1.  **Dữ liệu lớn**: Hãy thử thêm ít nhất **10 sinh viên** nữa.
    
**Thực hiện:** Thêm **15 sinh viên** mới.

```sql
INSERT INTO students (id, name, email, age) VALUES (3, 'Le Van C', 'levanc@example.com', 19);
INSERT INTO students (id, name, email, age) VALUES (4, 'Pham Thi D', 'phamd@example.com', 22);
INSERT INTO students (id, name, email, age) VALUES (5, 'Hoang Van E', 'hoange@example.com', 20);
INSERT INTO students (id, name, email, age) VALUES (6, 'Do Thi F', 'dof@example.com', 21);
INSERT INTO students (id, name, email, age) VALUES (7, 'Bui Van G', 'buig@example.com', 23);
INSERT INTO students (id, name, email, age) VALUES (8, 'Dang Thi H', 'dangh@example.com', 19);
INSERT INTO students (id, name, email, age) VALUES (9, 'Ngo Van I', 'ngoi@example.com', 22);
INSERT INTO students (id, name, email, age) VALUES (10, 'Vu Thi K', 'vuk@example.com', 20);
INSERT INTO students (id, name, email, age) VALUES (11, 'Dinh Van L', 'dinhl@example.com', 21);
INSERT INTO students (id, name, email, age) VALUES (12, 'Truong Thi M', 'truongm@example.com', 23);
INSERT INTO students (id, name, email, age) VALUES (13, 'Phan Van N', 'phann@example.com', 19);
INSERT INTO students (id, name, email, age) VALUES (14, 'Ly Thi O', 'lyo@example.com', 20);
INSERT INTO students (id, name, email, age) VALUES (15, 'Mai Van P', 'maip@example.com', 22);
INSERT INTO students (id, name, email, age) VALUES (16, 'Huynh Thi Q', 'huynhq@example.com', 21);
INSERT INTO students (id, name, email, age) VALUES (17, 'Vo Van R', 'vor@example.com', 23);
```

2.  **Ràng buộc Khóa Chính (Primary Key)**:
* Cố tình Insert một sinh viên có `id` trùng với một người đã có sẵn.
    ```sql
    -- Thêm một sinh viên có id trùng với sinh viên đã tồn tại
    INSERT INTO students (id, name, email, age) VALUES (17, 'Nguyen Le N', 'nln@example.com', 23);
    ```
    Database báo lỗi: 
    ```
    Execution finished with errors.
    Result: UNIQUE constraint failed: students.id
    ```

*  Quan sát thông báo lỗi: `UNIQUE constraint failed`. Tại sao Database lại chặn thao tác này?

    Vì thuộc tính id được cấu hình thành PRIMARY KEY của một bản ghi trong bảng students, do đó id là UNIQUE và NOT NULL. Việc thêm một student có id trừng lặp với một trong các id đã có trong bảng sẽ vi phạm constraints PRIMARY KEY của thuộc tính nên bị database chặn lại.


3.  **Toàn vẹn dữ liệu (Constraints)**:
*   Thử Insert một sinh viên nhưng bỏ trống cột `name` (để NULL).
    ``` sql
    - Thêm một sinh viên có thuộc tính name là NULL
    INSERT INTO students (id, name, email, age) VALUES (18, NULL, 'nothing@example.com', 20);
    ```
    *   Database có báo lỗi không? Từ đó suy nghĩ xem sự thiếu chặt chẽ này ảnh hưởng gì khi code Java đọc dữ liệu lên?
       
    Database báo insert dữ liệu thành công:
    ```
        Execution finished without errors.
        Result: query executed successfully. Took 0ms
    ```
        
* Khi Java đọc dữ liệu, nó sẽ đọc được bản ghi có thuộc tính là NULL và có nguy cơ gây lỗi khi chạy app :
    * NPE (NullPointerException) gây crash request hoặc Internal Server Error (code 500)
    * Lỗi logic khi xử lí NULL trên frontend gây thiệt hại về nghiệp vụ

4.  **Cấu hình Hibernate: Tại sao mỗi lần tắt ứng dụng và chạy lại, dữ liệu cũ trong Database lại bị mất hết?**

* Nguyên nhân nằm ở dòng cấu hình sau trong file application.properties:
```
  spring.jpa.hibernate.ddl-auto=create
```
* Với giá trị create, mỗi khi chạy ứng dụng, Hibernate sẽ thực hiện các bước:
  * Kiểm tra xem file student.db (SQLite) có các bảng dữ liệu chưa.
  * Xóa sạch (Drop) toàn bộ các bảng đó nếu chúng đã tồn tại.
  * Tạo mới (Create) lại các bảng trống dựa trên các class @Entity.
  * Vì vậy, mọi dữ liệu vừa nhập ở lần chạy trước đều bị xóa.

# Lab 2: Xây Dựng Backend REST API

## Kiểm tra API với trình duyệt
1.  **API lấy danh sách sinh viên**: GET http://localhost:8080/api/students
<img width="730" height="628" alt="image" src="https://github.com/user-attachments/assets/978df5c3-794f-4e26-a7e2-3f8ebb35c2eb" />
2. **API lấy chi tiết sinh viên**: 

