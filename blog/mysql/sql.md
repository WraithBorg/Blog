#### 根据指定顺序排序
```
SELECT
	emp_no
FROM
	employees
WHERE
	emp_no IN (
		'10001',
		'10002',
		'10004',
		'10005',
		'10003'
	)
ORDER BY
	FIND_IN_SET(
		emp_no,
		'10001,10003,10005,10002,10004'
	) ASC;
```