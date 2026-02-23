# Lab 1: Khởi Tạo & Kiến Trúc Hệ Thống
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
1. **API lấy danh sách sinh viên**: GET http://localhost:8080/api/students
<img width="730" height="628" alt="image" src="https://github.com/user-attachments/assets/978df5c3-794f-4e26-a7e2-3f8ebb35c2eb" />

2. **API lấy chi tiết sinh viên**: GET http://localhost:8080/api/students/2
<img width="726" height="297" alt="image" src="https://github.com/user-attachments/assets/f08b51cf-f7b2-4d40-a794-090268557ecb" />

3. **Lấy chi tiết sinh viên không tồn tại** GET http://localhost:8080/students/99
<img width="738" height="276" alt="image" src="https://github.com/user-attachments/assets/56886541-0b9b-4ad6-b1b2-8fa3c046e3af" />


# Lab 3: Xây dựng Frontend (SSR)
**Giao diện sẽ hiển thị danh sách các sinh viên và tô đỏ các bảng ghi sinh viên có tuổi < 18 (http://localhost:8080/students)**

<img width="1895" height="996" alt="image" src="https://github.com/user-attachments/assets/a140adb9-de5e-4487-85b1-8f53174bcc4d" />

**Chức năng tìm kiếm:** 

<img width="1909" height="349" alt="image" src="https://github.com/user-attachments/assets/662942b2-a131-44c5-9102-9b7bc2c6173b" />

# Lab 4: Hoàn thiện sản phẩm
**Module 1: Danh sách sinh viên**
* Đường dẫn: {URL}/students

<img width="1875" height="951" alt="image" src="https://github.com/user-attachments/assets/e035807b-d2d6-434c-a1d5-e1e0fc78b432" />

**Module 2: Trang chi tiết sinh viên**
* Đường dẫn: {URL}/students/id

Khi ấn vào nút xem chi tiết một dòng dữ liệu sinh viên bất kì, chuyển đến trang chi tiết thông tin của sinh viên đó
<img width="1912" height="535" alt="image" src="https://github.com/user-attachments/assets/79f06e3e-3b2b-44d7-a0cf-354f3aab6e73" />

**Tại trang này, ta có thể chỉnh sửa thông tin sinh viên hoặc xóa sinh viên.**
 * Khi ấn chỉnh sửa sinh viên, chuyển đến form sửa thông tin sinh viên.

  <img width="1905" height="748" alt="image" src="https://github.com/user-attachments/assets/d4ed0df4-e9d2-4066-9721-853d5c5331f7" />
  
 * Sau khi ấn lưu, chuyển hướng về trang thông tin chi tiết của sinh viên vừa được chỉnh sửa với thông tin đã được cập nhật.
   
  <img width="1915" height="504" alt="image" src="https://github.com/user-attachments/assets/518d545a-5f3b-4579-a24d-ab51c5a01221" />

 * Quay lại trang chi tiết sinh viên, khi ta ấn xóa, app sẽ hiện popup yêu cầu xác nhận
   <img width="1911" height="498" alt="image" src="https://github.com/user-attachments/assets/ecba51f6-3e23-4dd3-b012-06af1a230736" />
 * Sau khi bấm xóa, chuyển về trang danh sách sinh viên và bản ghi đã được xóa thành công (không còn tìm kiếm được sinh viên tên Nguyên)
   <img width="1915" height="436" alt="image" src="https://github.com/user-attachments/assets/e29171c4-5195-44c8-ab40-4d693d6f0a11" />

**Module 3: Trang thêm mới sinh viên**
*Đường dẫn: {URL}/students/create

Khi ấn nút thêm sinh viên, form tạo sinh viên sẽ hiện lên
  <img width="1916" height="798" alt="image" src="https://github.com/user-attachments/assets/dc6aea73-9ada-4e0a-b57b-9e008bcea73f" />
  
Sau khi ấn lưu, chuyển về trang danh sách sinh viên
  <img width="1872" height="491" alt="image" src="https://github.com/user-attachments/assets/29cad27c-fac2-45e5-88b7-780747f6324e" />



