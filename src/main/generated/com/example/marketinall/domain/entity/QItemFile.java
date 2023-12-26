package com.example.marketinall.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemFile is a Querydsl query type for ItemFile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemFile extends EntityPathBase<ItemFile> {

    private static final long serialVersionUID = 738201455L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemFile itemFile = new QItemFile("itemFile");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public final StringPath repimgYn = createString("repimgYn");

    public QItemFile(String variable) {
        this(ItemFile.class, forVariable(variable), INITS);
    }

    public QItemFile(Path<? extends ItemFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemFile(PathMetadata metadata, PathInits inits) {
        this(ItemFile.class, metadata, inits);
    }

    public QItemFile(Class<? extends ItemFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
    }

}

