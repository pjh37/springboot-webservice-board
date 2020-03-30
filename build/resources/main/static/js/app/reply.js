var reply={
    init:function(){
        var _this=this;

        $('#btn-reply-save').on('click',function(){
             _this.reply_save();
        });

    },reply_save:function(){
             var reply_id;
             var data={
                 id:$('#id').val(),
                 author:$('#author').val(),
                 content:$('#reply_content').val(),
                 re_reply_cnt:0
              };

             $.ajax({
                 type:'post',
                 url:'/api/v1/reply',
                 dataType:'json',
                 contentType:'application/json; charset=utf-8',
                 data: JSON.stringify(data),
                 success:function(data){
                    reply_id=data;
                 }
              }).done(function(){
                 $('#reply_content').val("");
                 var thumb=$('#thumb').attr('src');
                 var str="";
                 str+="<div class='reply' id=reply_"+reply_id+">"
                 +"<div class='reply_container'>"
                 +"<div class='thumb'>"
                 +"<img src="+thumb+" />"
                 +"</div>"
                 +" <div class='description'>"
                 +"<div class='header'>"
                 +"<span>"+data.author+"</span>"
                 +"<span id='modifiedDate'>방금</span>"
                 +"<div class='menu'>"
                 +"<a href='javascript:;' onClick='reply_update_display("+reply_id+")' >수정</a>"
                 +"<a href='javascript:;' onClick='reply_delete("+reply_id+")' >삭제</a>"
                 +"</div>"
                 +"</div>"
                 +" <div class='main'>"
                 +"<p id='content_"+reply_id+"'>"+data.content+"</p>"
                 +"</div>"
                 +"<div class='footer'>"
                 +" <a href='javascript:;' onClick='re_reply_display("+reply_id+")'>답글</a>"
                 +"</div>"
                 +"<div class='re_reply_list_"+reply_id+"'>"
                 +"</div>"
                 +" </div>"
                 +" <hr/>"
                 +"</div>"
                 +"</div>";
                 $('.reply_list').prepend(str);
                 //alert('댓글이 등록 완료.');
                 //window.location.href='/posts/read/'+$('#id').val();
              }).fail(function(error){
                 alert(JSON.stringify(error));
              });
     }
}
function reply_update(id){
     var data={
          content:$('#reply_update_content_'+id).val()
     };
    $.ajax({
        type:'PUT',
        url:'/api/v1/reply/'+id,
        dataType:'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(data)
      }).done(function(){

           $('.reply_update_display').remove();
           $('#content_'+id).text(data.content);
       }).fail(function(error){
         alert(JSON.stringify(error));
      });
}
function reply_update_display(id){
    var str="";
    str="<div class='reply_update_display'>"
        +"<form>"
        +"<div class='form-group'>"
        +"<textarea  class='form-control' id=reply_update_content_"+id+"></textarea>"
        +"</div>"
        +"</form>"
        +"<div class='menu'>"
        +"<button type='button' class='btn btn-primary btn_reply_update' onClick='reply_update("+id+")'>수정</button>"
        +"<button type='button' class='btn btn-danger' onClick='reply_display_delete'>취소</button>"
        +"</div>"
        +"</div>";
     $('#reply_'+id).append(str);
}
function reply_delete(id){
    //alert("댓글아이디 : "+id);
    $.ajax({
        type:'DELETE',
        url:'/api/v1/reply/'+id,
        dataType:'json',
        contentType:'application/json; charset=utf-8'
      }).done(function(){
         $('#reply_'+id).remove();
       }).fail(function(error){
         alert(JSON.stringify(error));
      });
}
function re_reply_display(id){
    var str="";
       str="<div class='re_reply_display'>"
           +"<form>"
           +"<div class='form-group'>"
           +"<textarea  class='form-control' id=re_reply_content_"+id+"></textarea>"
           +"</div>"
           +"</form>"
           +"<div class='menu'>"
           +"<button type='button' class='btn btn-primary btn_reply_update' onClick='re_reply_save("+id+")'>답글</button>"
           +"<button type='button' class='btn btn-danger' onClick='reply_display_delete()'>취소</button>"
           +"</div>"
           +"</div>";
        $('#reply_'+id).append(str);
}
function re_reply_save(reply_id){
     var parent=reply_id;
     var re_reply_id;
        var data={
            id:$('#id').val(),
            parent:parent,
            author:$('#author').val(),
            content:$('#re_reply_content_'+parent).val(),
            re_reply_cnt:0
         };
        $.ajax({
             type:'post',
             url:'/api/v1/reply',
             dataType:'json',
             contentType:'application/json; charset=utf-8',
             data: JSON.stringify(data),
             success:function(data){
                 re_reply_id=data;
             }
        }).done(function(){
            $('.re_reply_display').remove();
            var thumb=$('#thumb').attr('src');
            var str="";
             str+="<div class='reply' id=reply_"+re_reply_id+">"
             +"<div class='reply_container'>"
             +"<div class='thumb'>"
             +"<img src="+thumb+" />"
             +"</div>"
             +" <div class='description'>"
             +"<div class='header'>"
             +"<span>"+data.author+"</span>"
             +"<span id='modifiedDate'>방금</span>"
             +"<div class='menu'>"
             +"<a href='javascript:;' onClick='reply_update_display("+re_reply_id+")' >수정</a>"
             +"<a href='javascript:;' onClick='reply_delete("+re_reply_id+")' >삭제</a>"
             +"</div>"
             +"</div>"
             +" <div class='main'>"
             +"<p id='content_"+re_reply_id+"'>"+data.content+"</p>"
             +"</div>"
             +"<div class='footer'>"
             +" <a href='javascript:;' onClick='re_reply_display("+parent+")'>답글</a>"
             +"</div>"
             +" </div>"
             +" <hr/>"
             +"</div>"
             +"</div>";
             $('.re_reply_list_'+parent).prepend(str);
        }).fail(function(error){
             alert(JSON.stringify(error));
        });
}
function reply_display_delete(){
    $('.re_reply_display').remove();
    $('.reply_update_display').remove();

}
reply.init();