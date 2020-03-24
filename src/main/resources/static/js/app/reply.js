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
                 content:$('#reply_content').val()
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
                 +" <a href='#'>답글</a>"
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
     },reply_update_display:function(){

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
        +"<button type='button' class='btn btn-primary' onClick='reply_update("+id+")'>수정</button>"
        +"<button type='button' class='btn btn-danger'>취소</button>"
        +"</div>"
        +"</div>";
     $('#reply_'+id).append(str);
}
/*
 <div class="reply_write">
        <!-- 댓글 작성  -->
        <form>
            <div class="form-group">
                <label for="author">작성자</label>
                <input type="text" class="form-control" id="author"  value="{{post.author}}" readonly>
            </div>
            <div class="form-group">
                <label for="reply_content">댓글</label>
                <textarea  class="form-control" id="reply_content"></textarea>
            </div>
        </form>
        <button type="button" class="btn btn-primary" id="btn-reply-save">등록</button>
    </div>


*/
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
reply.init();