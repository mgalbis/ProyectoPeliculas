
<h1>Pel�culas</h1>
<div id="datos"></div>

<script>
    $(document).ready(function() {      
        
       done++;
       $.when(getAll('peliculas')).done(function(d){
                

                $.each(d['list'], function(index, value){
                    var btn = '<div class="btn-group">'
                    //botones. params: id, class, content
                        + createBtn(value['#'], 'ver', '<i class="icon-eye-open"></i>')
                        + createBtn(value['#'], 'editar', '<i class="icon-pencil"></i>')
                        + createBtn(value['#'], 'eliminar', '<i class="icon-trash"></i>')
                        + '</div>';
                    
                    value['Acci�n'] = btn;
                    
                    delete value['Descripci�n'];
                    delete value['Duraci�n'];
                    delete value['Calificaci�n'];
                    delete value['Actores'];
                    delete value['V.O.'];
                    
                    value['Director'] = value['Director']['nombre'];
                    value['G�nero'] = value['G�nero']['nombre'];
                });
                
                //params: id, class, contenido(array[{}]) 
                var table = createTable('peliculas', '', d['list']);
                $('#datos').append(table);
  
  
                $('.ver').click(function(){
                    window.location = 'peliculas/view.html?id='+$(this).attr('id');
                });
                $('.editar').click(function(){
                    window.location = 'peliculas/form.html?id='+$(this).attr('id');
                });
                $('.eliminar').click(function(){
                    confirmDelete('peliculas', $(this).attr('id'));
                });
                
                done--;
           
        });
        
        
    });
</script>

