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
         }).fail(function(error){
            alert(JSON.stringify(error));
         });
    }
};
index.init();