

<% Integer id = (Integer) request.getAttribute("id");%>
<form class="form-horizontal">
    <fieldset>
        <legend><%=(id == 0 ? "Crear" : "Editar")%> Pel�cula</legend>
        <div class="control-group">
            <label class="control-label" for="T�tulo">T�tulo</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="T�tulo">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="V.O.">V.O.</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="VO">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Fecha">Fecha</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="Fecha">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Descripci�n">Descripci�n</label>
            <div class="controls">
                <textarea class="input-xlarge" id="Descripci�n" rows="3"></textarea>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Duraci�n">Duraci�n (min.)</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="Duraci�n">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Calificaci�n">Calificaci�n</label>
            <div class="controls">
                <input type="text" class="input-xlarge" id="Calificaci�n">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="G�nero">G�nero</label>
            <div class="controls">
                <select id="G�nero">
                </select>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="Director">Director</label>
            <div class="controls">
                <select id="Director">
                </select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="Actores">Actores</label>
            <div class="controls">
                <select multiple="multiple" id="Actores">
                </select>
            </div>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Guardar cambios</button>
            <a class="btn" href="peliculas/index.html">Cancelar</a>
        </div>
    </fieldset>
</form>
<script>
    $(document).ready(function() { 
        done++;
        $.when(get('peliculas', <%=id%>)).done(function(d){
            $('form').append(createInput('hidden', 'id', 'id', ''));
            
            $.each(d, function(index, value){ if(index == '#') index = 'id'; })
            
            $('#Descripci�n').text(d['Descripci�n']);
            $('#T�tulo').val(d['T�tulo']);
            $('#VO').val(d['VO']);
            $('#Fecha').val(d['Fecha']);
            $('#Duraci�n').val(d['Duraci�n']);
            $('#Calificaci�n').val(d['Calificaci�n']);
            $('#id').val(d['id']);
        
           
           
           //carga combo Genero
           $.when(getAll('generos')).done(function(a){
               cargaCombo('G�nero', a['list']);
           })
           
           //carga combo Director
           $.when(getAll('directores')).done(function(a){
               cargaCombo('Director', a['list']);
           })
           
           //carga lista actores
          $.when(getAll('actores')).done(function(a){
               cargaCombo('Actores', a['list']);
           })

       
            done--;
        })
    })
</script>