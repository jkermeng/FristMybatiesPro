<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>美化H5上传标签</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        .upload-box {
            position: relative;
            padding: 25px;
            width: 400px;
            height: 150px;
            border: 1px solid salmon;
            margin: 0 auto;
        }

        /* input的按钮样式 */
        .original-upload {
            position: absolute;
            top: 25px;
            left: 25px;
            width: 134px;
            height: 42px;
            opacity: 1;
            z-index: 3;
            cursor: pointer;
        }

        /* 假按钮样式，自己发挥 */
        .fake-uploadbtn {
            display: block;
            width: 134px;
            line-height: 42px;
            background-color: #ADD8E6;
            color: #fff;
            text-decoration: none;
            text-align: center;
            border-radius: 12px;
            font-size: 18px;
        }
    </style>
</head>

<body>
<div class="upload-box">
    <form name="file" action="/firstBatis/upload" enctype="multipart/form-data" method="post">
        <input type="file"  name="upfile" id="upfile" class="original-upload" />
        <button type="submit" style="position: absolute;bottom :100px;">确定</button>
    </form>
<%--    <a href="javascript:;" class="fake-uploadbtn">上传文件</a>--%>
</div>
</body>

</html>
