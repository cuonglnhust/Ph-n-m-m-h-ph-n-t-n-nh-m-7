# LTU14-GROUP6
Phát triển phần mềm hệ phân tán nhóm 6

## A.Giới thiệu 
- Game cá ngựa được phát triển theo phầm mềm hệ phân tán
- Sử dụng JAVA RMI để phát triển
- Triển khai trên docker
- Sử dụng mySQL làm hệ quản trị cơ sở dữ liệu

## B.Cài đặt môi trường
### 1. Chuẩn bị môi trường để cài đặt docker

**Bước 1 :** Cài đặt hệ điều hành Linux mint hoặc hệ hiều hành Ubuntu phiên bản 16.04 hoặc phiên bản cũ hơn <br/>
**Bước 2 :** Cài đặt JAVA 11 lên hệ điều hành trên: <br/>
        - Chạy lệnh sau: <br/>
            `sudo apt install openjdk-11-jdk`<br/>
**Bước 3 :** Cài đặt intelliJ cho hệ điều hành trên: <br/>
        - Truy cập vào link ở dưới để có thể tải intelliJ cho linux <br/>
        https://www.jetbrains.com/idea/download/#section=linux <br/>
**Bước 4 :** Cài đặt docker cho hệ điều hành trên: <br/>
        - Chạy lệnh sau để update gói apt-get: <br/>
            `sudo apt-get update`<br/>
        - Chạy lệnh sau để uninstall docker cũ nếu có: <br/>
            `sudo apt-get remove docker docker-engine docker.io`<br/>
        - Chạy lệnh sau để cài đặt docker mới: <br/>
            `sudo apt install docker.io`<br/>
        - Chạy lệnh sau để khởi động docker: <br/>
            `sudo systemctl start docker`<br/>
            `sudo systemctl enable docker`<br/>
        - Sau khi chạy lệnh trên nếu không có lỗi gì xảy ra là đã hoàn thành cài đặt docker. Bây giờ bạn có thể kiểm tra xem docker phiên bản bao nhiêu bằng lệnh: <br/>
            `sudo docker --version`<br/>
**Bước 5 :** Clone RMI server <br/>
        - Clone project trên về thư mục của máy với nhánh dev <br/>
        - Mở project vừa clone về trên bằng IntelliJ và file jar driver cho mysql server 8 <br/>
        - Build project trên ra file jar <br/>
**Bước 6 :** Clone RMI client <br/>
        - Clone project trên về thư mục của máy với nhanh tung <br/>
        - Mở project vừa clone được trên bằng IntelliJ <br/>
        - Build project trên ra file jar <br/>

### 2. Chuẩn bị môi trường docker để trên khai <br/>

**Bước 1 :** Tạo 1 container để cài cơ sở dữ liệu <br/>
        - Sử dụng câu lệnh sau để pull image từ dockerhub <br/>
            `sudo docker pull mysql`<br/>
        - Thông số cần nhớ :MYSQL 8.* <br/>
	            +, port: 3306 <br/>
	            +, file config: /etc/mysql/my.cnf <br/>
		            [mysqld] <br/>
		            default-authentication-plugin=mysql_native_password <br/>
	            +,root:MYSQL_ROOT_PASSWORD <br/>
	            +, database: /var/lib/mysql: thư mục chưa db <br/>
        -Bắt đầu install và setup: <br/>
                +,B1: tạo container từ image: docker run -e MYSQL_ROOT_PASSWORD -v <thư mục lưu db trên host>:</var/lib/mysql> --name <tên của container> <id của image> <br/>
                +,B2: thay đổi file my.cnf: do là mysql 8.* nên có cơ chế xác thực mới. Vì vậy cần config lại một chút để các phiên bản thấp hơn vẫn có thể truy cập vào mysql server này <br/>
                +,B3: Chạy lệnh sau để vào container do khi tạo không sử dụng -it: docker exec -it 
                <id của container> bash <br/>
                +,B4: chạy mysql -u <Tên đăng nhập vào mysql (default là root)>  -p 
                <password(của root là MYSQL_ROOT_PASSWORD)> <br/>
                **LƯU Ý: khi nhập password thì -p sau đó đến password luôn. KHông có dấu cách <br/>
                +,B5: Chạy thử một số lệnh database như show databases; .... <br/>

**Bước 2 :**: Pull image ubuntu 18.04 đã được cài java trên dockerhub <br/>
        - Chạy lệnh sau để pull <br/>
            `sudo docker pull killeretile/java8jdkjre:v11`<br/>
**Bước 3 :**: Tạo ra 1 container để chạy server <br/>
        - Chạy lệnh sau để tạo container chạy server <br/>
            `sudo docker run -it <id của image vừa tải về>` <br/>
        - Copy thư mục chưa server vừa clone từ git về vào container server này <br/>
            `sudo docker cp <thư mục host> <id của container>:<thư mục trong container>`<br/>
        - Chạy file .jar trong thư mục đó để chạy server <br/>
            `java -jar <tên file .jar>`<br/>

**Bước 4 :** Chạy câu lệnh sau ở host để chia sẻ giao diện <br/>
            `xhost +`<br/>

**Bước 5 :**: Tạo container cho client <br/>
        - Chạy lệnh sau để tạo container cho client <br/>
            `sudo docker run -it DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix <id của image> `<br/>
        - Copy folder của thư mục host vào container và chạy như bên server <br/>
        - Sau khi chạy sẽ hiển thị giao diện game và có thể bắt đầu chơi khi có người khác đăng nhập vào <br/>
**Bước 6 :** Làm như bước 5 để tạo client thứ 2 vào hệ thống để chơi với client 1 <br/>
