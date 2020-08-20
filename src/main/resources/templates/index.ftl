<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css">

    <link type="text/css" rel="stylesheet" href="css/bootstrap-theme.min.css">
    <#--    <!-- Latest compiled and minified CSS &ndash;&gt;
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme &ndash;&gt;
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
              integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">-->
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1 class="page-header" style="padding-bottom: 5px; padding-top: 5px">Hello!</h1>
            <h2><p style="color: #e83e8c">
                    You are logged with username:
                    <span style="padding-left: 10px; color: #6f42c1"> ${username}</span></p>
                <p style="color: #e83e8c"> and with roles:
                    <span style="padding-left: 10px; color: #6f42c1">  ${roles}  </span></p>
            </h2>
        </div>
        <div>
            <p>
                <a href="/login?logout"
                   style="color:purple;
                   font-style:italic;">
                    Выход из системы
                </a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
