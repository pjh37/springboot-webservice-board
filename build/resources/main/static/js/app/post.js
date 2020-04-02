var post={
    init:function(){
        var _this=this;
        _this.paging();
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
    },paging:function(){
        if($('#lastPage').length>0&&$('#curPage').length>0){
             var lastPage=$('#lastPage').attr('href').substr(7);
                    var curPage=$('#curPage').attr('href').substr(7);
                    var startPage=curPage-5>0 ? curPage-5 : 1;
                    var endPage=curPage+4>lastPage ? lastPage : curPage+4;
                    var groupPage=10;
                    $('#page').empty();
                    for(i=startPage;i<=endPage;i++){
                        var str="";
                        str+="<li class='page-item'>"+
                        "<a class='page-link'  href=/board/"+i+">"+
                        i+
                        "</a>"+
                        "</li>";
                        $('#page').append(str);
                    }

        }

    }
};
post.init();

