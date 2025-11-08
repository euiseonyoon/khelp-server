package com.luke.kHelperServer.application.db_synchronizer.required_port

interface DocumentRepository <TDocument> {
    fun save(document: TDocument)

    fun findByWriteEntityId(writeEntityId: Long): TDocument?

    fun deleteByWriteEntityId(writeEntityId: Long)
}
