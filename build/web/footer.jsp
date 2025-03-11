<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<footer class="footer bg-dark text-white py-4">
    <div class="container text-center">
        <!-- Logo with Link -->
        <a href="rental.jsp">
            <img src="images/logo.jpg" alt="Company Logo" style="width: 150px;">
        </a>

        <!-- Social Media Icons -->
        <div class="mt-3">
            <a href="https://www.facebook.com/nguyenminhsang0101" target="_blank" class="text-white mx-2">
                <i class="fab fa-facebook fa-lg"></i>
            </a>
            <a href="https://www.instagram.com/trg.kienne/" target="_blank" class="text-white mx-2">
                <i class="fab fa-instagram fa-lg"></i>
            </a>
            <a href="https://x.com/T1" target="_blank" class="text-white mx-2">
                <i class="fab fa-twitter fa-lg"></i>
            </a>
        </div>

        <p>&copy; PRJ301 FA24 Vehicle Hiring System..</p>
    </div>
</footer>

<!-- Font Awesome Icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<!-- CSS for Sticky Footer -->
<style>
    /* Cấu trúc flexbox cho footer sticky */
    html, body {
        height: 100%;
        margin: 0;
        display: flex;
        flex-direction: column;
    }
    .container-fluid {
        flex: 1;
        padding-bottom: 100px; /* Tạo không gian cho footer */
    }
    .footer {
        width: 100%;
        position: relative;
    }
</style>