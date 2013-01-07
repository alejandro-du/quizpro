1,new Hash('contents','Convocatoria','uri','',
    1,new Hash('contents','Autenticación','uri','/quizpro/autenticacion/inicio.action')
2,new Hash('contents','Sesión','uri','',
    1,new Hash('contents','Seleccionar convocatoria','uri','/quizpro/convocatoria/seleccionarConvocatoria.action')))



domMenu_data.set('domMenu_BJ', new Hash(
    
    1, new Hash('contents', 'Convocatoria'
        
    ),
    
    
    2, new Hash('contents', 'Reportes'
        
    ),
    
    
    3, new Hash('contents', 'Sesión',
        
        1, new Hash(
            'contents', 'Cambiar de convocatoria',
            'uri', 'http://localhost:8080/quizpro/convocatoria/seleccionarConvocatoria.action'
        ),
        
        2, new Hash(
            'contents', 'Actualizar datos',
            'uri', 'http://www.example.com'
        ),
        
        3, new Hash(
            'contents', 'Cerrar sesión',
            'uri', 'http://www.example.com'
        )
    )
));
