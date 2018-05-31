package com.hamburgueria.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUsuario is a Querydsl query type for Usuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = 1966572422L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final NumberPath<Integer> creditos = createNumber("creditos", Integer.class);

    public final DateTimePath<java.util.Date> dataNascimento = createDateTime("dataNascimento", java.util.Date.class);

    public final StringPath email = createString("email");

    public final StringPath foto64 = createString("foto64");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idPadrinho = createNumber("idPadrinho", Long.class);

    public final StringPath nome = createString("nome");

    public final EnumPath<Papel> papel = createEnum("papel", Papel.class);

    public final ListPath<Pedido, QPedido> pedidos = this.<Pedido, QPedido>createList("pedidos", Pedido.class, QPedido.class, PathInits.DIRECT2);

    public final QSede sede;

    public final StringPath senha = createString("senha");

    public final StringPath telefone = createString("telefone");

    public QUsuario(String variable) {
        this(Usuario.class, forVariable(variable), INITS);
    }

    public QUsuario(Path<? extends Usuario> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUsuario(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUsuario(PathMetadata<?> metadata, PathInits inits) {
        this(Usuario.class, metadata, inits);
    }

    public QUsuario(Class<? extends Usuario> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
    }

}

