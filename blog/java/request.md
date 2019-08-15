 response.sendRedirect(request.getContextPath()); 
 运用forward方法只能重定向到同一个Web应用程序中的一个资源。而sendRedirect方法可以让你重定向到任何URL
 redirect   会首先发一个response给浏览器,   然后浏览器收到这个response后再发一个requeset给服务器,   然后服务器发新的response给浏览器.   这时页面收到的request是一个新从浏览器发来的.
 forward   发生在服务器内部,   在浏览器完全不知情的情况下发给了浏览器另外一个页面的response.   这时页面收到的request不是从浏览器直接发来了,可能己经放了数据.
 所以: 
         request.setAttribute存的东西
         只用通过方法2跳转   才能在新页取出来