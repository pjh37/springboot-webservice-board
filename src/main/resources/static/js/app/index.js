var index={
    init:function(){
        var _this=this;
        $(document).ready(function() {
            $(".visual").css("background-image", "url('/images/ocean.jpg')");
        })
        $('#btn-save').on('click',function(){
            _this.save();
        });
         $('#btn-update').on('click',function(){
            _this.update();
        });
         $('#btn-delete').on('click',function(){
            _this.delete();
         });
         $('#btn-reply-save').on('click',function(){
            _this.reply_save();
         });
    },
    save :function(){
        var data={
            title:$('#title').val(),
            author:$('#author').val(),
            content:$('#content').val()
        };
        $.ajax({
            type:'POST',
            url:'/api/v1/posts',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록됬습니다');

            window.location.href='/board';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },update:function(){
         var data={
                    title:$('#title').val(),
                    content:$('#content').val()
         };
         var id=$('#id').val();
         $.ajax({
            type:'PUT',
            url:'/api/v1/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
         }).done(function(){
                 alert('글이 수정됬습니다');
                 window.location.href='/board';
         }).fail(function(error){
                 alert(JSON.stringify(error));
         });
    },delete:function(){
        var id=$('#id').val();
        $.ajax({
             type:'DELETE',
             url:'/api/v1/posts/'+id,
             dataType:'json',
             contentType:'application/json; charset=utf-8'
         }).done(function(){
              alert('글이 삭제됬습니다');
              window.location.href='/board';
         }).fail(function(error){
              alert(JSON.stringify(error));
         });
    },reply_save:function(){
        var data={
            id:$('#id').val(),
            author:$('#author').val(),
            content:$('#reply_content').val()
         };
        $.ajax({
            type:'post',
            url:'/api/v1/reply',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
         }).done(function(){
            $('#reply_content').val("");
            var thumb=$('#thumb').attr('src');
            var str="";
            str+="<div class='reply'>"
            +"<div class='thumb'>"
            +"<img src="+thumb+" />"
            +"</div>"
            +" <div class='description'>"
            +"<div class='header'>"
            +"<span>"+data.author+"</span>"
            +"<span>방금</span>"
            +"<div class='menu'>"
            +"<a href='javascript:reply_update()'; >수정</a>"
            +"<a href='javascript:reply_delete()'; >삭제</a>"
            +"</div>"
            +"</div>"
            +" <div class='main'>"
            +"<p>"+data.content+"</p>"
            +"</div>"
            +"<div class='footer'>"
            +" <a href='#'>답글</a>"
            +"</div>"
            +" </div>"
            +" <hr/>"
            +"</div>";
            $('.reply_list').prepend(str);
            //alert('댓글이 등록 완료.');
            //window.location.href='/posts/read/'+$('#id').val();
         }).fail(function(error){
            alert(JSON.stringify(error));
         });
    }
};
index.init();

