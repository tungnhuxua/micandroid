<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:param name="param1" />
	<xsl:param name="param2" />

	<xsl:template match="/">test<xsl:value-of select="$param1" /><xsl:value-of select="$param2" />
	</xsl:template>

</xsl:stylesheet>