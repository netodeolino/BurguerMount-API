package com.hamburgueria.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPedido is a Querydsl query type for Pedido
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPedido extends EntityPathBase<Pedido> {

    private static final long serialVersionUID = 461052909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPedido pedido = new QPedido("pedido");

    public final QUsuario cliente;

    public final DateTimePath<java.util.Date> data = createDateTime("data", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath local = createString("local");

    public final StringPath mensagem = createString("mensagem");

    public final NumberPath<Double> preco = createNumber("preco", Double.class);

    public final ListPath<Produto, QProduto> produtos = this.<Produto, QProduto>createList("produtos", Produto.class, QProduto.class, PathInits.DIRECT2);

    public final QSede sede;

    public final EnumPath<Status> status = createEnum("status", Status.class);

    public final NumberPath<Double> troco = createNumber("troco", Double.class);

    public QPedido(String variable) {
        this(Pedido.class, forVariable(variable), INITS);
    }

    public QPedido(Path<? extends Pedido> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPedido(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPedido(PathMetadata<?> metadata, PathInits inits) {
        this(Pedido.class, metadata, inits);
    }

    public QPedido(Class<? extends Pedido> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cliente = inits.isInitialized("cliente") ? new QUsuario(forProperty("cliente"), inits.get("cliente")) : null;
        this.sede = inits.isInitialized("sede") ? new QSede(forProperty("sede")) : null;
    }

}

