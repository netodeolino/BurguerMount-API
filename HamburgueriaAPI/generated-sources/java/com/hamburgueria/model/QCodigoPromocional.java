package com.hamburgueria.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCodigoPromocional is a Querydsl query type for CodigoPromocional
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCodigoPromocional extends EntityPathBase<CodigoPromocional> {

    private static final long serialVersionUID = 1268729726L;

    public static final QCodigoPromocional codigoPromocional = new QCodigoPromocional("codigoPromocional");

    public final StringPath codigo = createString("codigo");

    public final NumberPath<Integer> creditos = createNumber("creditos", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxUso = createNumber("maxUso", Integer.class);

    public final ListPath<Usuario, QUsuario> usuarios = this.<Usuario, QUsuario>createList("usuarios", Usuario.class, QUsuario.class, PathInits.DIRECT2);

    public QCodigoPromocional(String variable) {
        super(CodigoPromocional.class, forVariable(variable));
    }

    public QCodigoPromocional(Path<? extends CodigoPromocional> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCodigoPromocional(PathMetadata<?> metadata) {
        super(CodigoPromocional.class, metadata);
    }

}

