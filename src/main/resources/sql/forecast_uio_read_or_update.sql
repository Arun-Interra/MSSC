MERGE INTO MMAT.SSC_UIO_DATA AS tab 
USING (VALUES
        ( ?,?,?,?,?,?,NOW(),? ,NOW())
    ) AS merge (YEAR, MONTH, DLR_CD , UIO_TYPE, UIO_VAL, CREATED_BY, CREATE_TM, LAST_UPDATED_BY, LAST_UPDATE_TM)
    ON tab.DLR_CD = merge.DLR_CD
    WHEN MATCHED THEN
        UPDATE SET tab.YEAR = merge.YEAR,
                   tab.MONTH = merge.MONTH,
                   tab.DLR_CD = merge.DLR_CD,
                   tab.UIO_TYPE = merge.UIO_TYPE,
                   tab.UIO_VAL = merge.UIO_VAL,
                   tab.CREATED_BY = merge.CREATED_BY,
                   tab.LAST_UPDATED_BY = merge.LAST_UPDATED_BY
    WHEN NOT MATCHED THEN
        INSERT (YEAR, MONTH, DLR_CD , UIO_TYPE, UIO_VAL, CREATED_BY, CREATE_TM, LAST_UPDATED_BY, LAST_UPDATE_TM)
        VALUES (merge.YEAR,merge.MONTH,merge.DLR_CD,merge.UIO_TYPE,merge.UIO_VAL,merge.CREATED_BY,
                NOW(),merge.LAST_UPDATED_BY,NOW())
