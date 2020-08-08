<?xml version="1.0" encoding="gb2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <!-- ��ģ��-->

    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:value-of select="����/������Ϣ/����"/>�ĸ��˼���
                </title>
            </head>
            <body>
                <h2 align="center">����</h2>
                <xsl:apply-templates select="����"/>
            </body>
        </html>
    </xsl:template>

    <!--����ģ��-->
    <xsl:template match="����">
        <xsl:apply-templates select="������Ϣ"/>
        <br/>
        <xsl:apply-templates select="������Ϣ/ѧϰ�׶�"/>
        <br/>
        <xsl:apply-templates select="������Ϣ"/>
        <br/>
        <xsl:apply-templates select="������Ϣ/��У����"/>
    </xsl:template>

    <!--������Ϣģ��-->
    <xsl:template match="������Ϣ">
        <table align="center" width="800" border="0">
            <tbody>
                <tr>
                    <th colspan="7" bgcolor="#9aff32" align="left">������Ϣ</th>
                </tr>
                <tr>
                    <td width="35">����:</td>
                    <td width="50">
                        <font color="gray"><xsl:value-of select="����"/></font>
                    </td>
                    <td width="35">�Ա�:</td>
                    <td width="40">
                        <font color="gray"><xsl:value-of select="�Ա�"/></font>
                    </td>
                    <td width="35">��������:</td>
                    <td width="80">
                        <font color="gray"><xsl:value-of select="��������"/></font>
                    </td>
                    <td width="150" rowspan="2">
                        <img src="F:\IDEA\javalearning\src\javaEE\zzl2018.png" width="80" height="100px"/>
                    </td>
                </tr>
                <tr>
                    <td>��ͥסַ:</td>
                    <td>
                        <font color="gray"><xsl:value-of select="��ͥסַ"/></font>
                    </td>
                    <td>���˵绰:</td>
                    <td>
                        <font color="gray"><xsl:value-of select="���˵绰"/></font>
                    </td>
                    <td>��ϵ�˵绰:</td>
                    <td>
                        <font color="gray"><xsl:value-of select="��ϵ�˵绰"/></font>
                    </td>
                </tr>
            </tbody>
        </table>

    </xsl:template>
    <!--����ģ��-->

    <!--����ģ��-->
    <xsl:template match="������Ϣ">
        <table width="800" align="center" style="border-collapse:separate; border-spacing:0px 5px;">
            <tbody>
                <tr>
                    <th colspan="5" bgcolor="#9aff32" align="left">��������</th>
                </tr>
                <tr>
                    <td>��˾����</td>
                    <td>ְλ</td>
                    <td>��ʼʱ��</td>
                    <td>��ֹʱ��</td>
                    <td>ְλ����</td>
                </tr>
                <xsl:apply-templates select="��������/����"/>
            </tbody>
        </table>
    </xsl:template>
    <xsl:template match="��������/����">
        <tr style="color:gray">
            <td>
                <xsl:value-of select="��˾����"/>
            </td>
            <td>
                <xsl:value-of select="ְλ"/>
            </td>
            <td>
                <xsl:value-of select="��ʼʱ��"/>
            </td>
            <td>
                <xsl:value-of select="��ֹʱ��"/>
            </td>
            <td width="350">
                <xsl:value-of select="ְλ����"/>
            </td>
        </tr>
    </xsl:template>

    <!--��У���-->
    <xsl:template match="������Ϣ/��У����">
        <table width="800" align="center" style="border-collapse:separate; border-spacing:0px 5px;">
            <tbody>
                <tr>
                    <th colspan="5" bgcolor="#9aff32" align="left">��У���</th>
                </tr>
                <tr>
                    <td>���˰���</td>
                    <td>�������</td>
                    <td>���з����������</td>
                    <td>���Ż���</td>
                </tr>
                    <xsl:apply-templates select="���˰���"/>
                    <xsl:apply-templates select="�������"/>
                    <xsl:apply-templates select="���з����������"/>
                    <xsl:apply-templates select="���Ż���"/>
            </tbody>
        </table>
    </xsl:template>
    <xsl:template match="���˰���">
        <td>
            <font color="gray"><xsl:value-of select="����"/></font>
        </td>
    </xsl:template>
    <xsl:template match="�������">
        <td>
            <font color="gray"><xsl:value-of select="����"/></font>
        </td>
    </xsl:template>
    <xsl:template match="���з����������">
        <td>
            <font color="gray"><xsl:value-of select="����/���±���"/></font>
        </td>
    </xsl:template>
    <xsl:template match="���Ż���">
        <td>
            <font color="gray"><xsl:value-of select="�"/></font>
        </td>
    </xsl:template>
    <!--    <xsl:template match="���˰���">
            <tr style="color:gray">
                <td>
                    <xsl:value-of select="������Ϣ/���˰���/����"/>
                </td>
                <td>
                    <xsl:value-of select="������Ϣ/�������/����"/>
                </td>
                <td>
                    <xsl:value-of select="������Ϣ/���з����������/����/���±���"/>
                </td>
                <td>
                    <xsl:value-of select="������Ϣ/���Ż���/�"/>
                </td>
            </tr>
        </xsl:template>-->


    <!--��������ģ��-->
    <xsl:template match="��������">

        <table width="800" align="center">

            <tbody>

                <tr>

                    <th bgcolor="#CAE1FF" align="left">��������</th>

                </tr>

                <tr>

                    <td>
                        <xsl:value-of select="."/>
                    </td>

                </tr>

            </tbody>

        </table>

    </xsl:template>
</xsl:stylesheet>