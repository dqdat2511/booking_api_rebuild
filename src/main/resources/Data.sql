PGDMP         3                {         
   Db_Booking    15.4    15.4 *    7           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            8           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            9           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            :           1262    31092 
   Db_Booking    DATABASE     �   CREATE DATABASE "Db_Booking" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Vietnamese_Vietnam.1258';
    DROP DATABASE "Db_Booking";
                postgres    false            �            1259    31147 
   bus_floors    TABLE     Z   CREATE TABLE public.bus_floors (
    id uuid NOT NULL,
    name character varying(255)
);
    DROP TABLE public.bus_floors;
       public         heap    postgres    false            �            1259    31555    bus_slot    TABLE     �   CREATE TABLE public.bus_slot (
    id uuid NOT NULL,
    is_available boolean DEFAULT true NOT NULL,
    name_slot character varying(255),
    trip_id uuid,
    bus_floor_id uuid,
    bus_type_id uuid
);
    DROP TABLE public.bus_slot;
       public         heap    postgres    false            �            1259    31219 	   bus_types    TABLE     �   CREATE TABLE public.bus_types (
    id uuid NOT NULL,
    convenients text,
    maxslot integer NOT NULL,
    name character varying(255),
    numbers_floor integer NOT NULL,
    number_plate character varying(255)
);
    DROP TABLE public.bus_types;
       public         heap    postgres    false            �            1259    31561    customer_tickes    TABLE     k   CREATE TABLE public.customer_tickes (
    customer_id uuid NOT NULL,
    customer_seat_id uuid NOT NULL
);
 #   DROP TABLE public.customer_tickes;
       public         heap    postgres    false            �            1259    31514    customer_tickets    TABLE     ?   CREATE TABLE public.customer_tickets (
    id uuid NOT NULL
);
 $   DROP TABLE public.customer_tickets;
       public         heap    postgres    false            �            1259    31564    tickets    TABLE       CREATE TABLE public.tickets (
    id uuid NOT NULL,
    address character varying(255),
    code_ticket character varying(255),
    customer_name character varying(255),
    customer_phone character varying(255),
    num_tickets integer NOT NULL,
    trip_id uuid
);
    DROP TABLE public.tickets;
       public         heap    postgres    false            �            1259    31417 	   time_trip    TABLE     �   CREATE TABLE public.time_trip (
    id integer NOT NULL,
    end_day date,
    end_time time(6) without time zone,
    start_day date,
    start_time time(6) without time zone
);
    DROP TABLE public.time_trip;
       public         heap    postgres    false            �            1259    31416    time_trip_id_seq    SEQUENCE     �   CREATE SEQUENCE public.time_trip_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.time_trip_id_seq;
       public          postgres    false    217            ;           0    0    time_trip_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.time_trip_id_seq OWNED BY public.time_trip.id;
          public          postgres    false    216            �            1259    31571    trips    TABLE     �   CREATE TABLE public.trips (
    id uuid NOT NULL,
    name character varying(255),
    timetrip_id integer,
    bus_type_id uuid
);
    DROP TABLE public.trips;
       public         heap    postgres    false            �           2604    31420    time_trip id    DEFAULT     l   ALTER TABLE ONLY public.time_trip ALTER COLUMN id SET DEFAULT nextval('public.time_trip_id_seq'::regclass);
 ;   ALTER TABLE public.time_trip ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    217    217            3           2613    31236    31236    BLOB     &   SELECT pg_catalog.lo_create('31236');
 &   SELECT pg_catalog.lo_unlink('31236');
                postgres    false            *          0    31147 
   bus_floors 
   TABLE DATA           .   COPY public.bus_floors (id, name) FROM stdin;
    public          postgres    false    214   /0       /          0    31555    bus_slot 
   TABLE DATA           c   COPY public.bus_slot (id, is_available, name_slot, trip_id, bus_floor_id, bus_type_id) FROM stdin;
    public          postgres    false    219   �0       +          0    31219 	   bus_types 
   TABLE DATA           `   COPY public.bus_types (id, convenients, maxslot, name, numbers_floor, number_plate) FROM stdin;
    public          postgres    false    215   3n       0          0    31561    customer_tickes 
   TABLE DATA           H   COPY public.customer_tickes (customer_id, customer_seat_id) FROM stdin;
    public          postgres    false    220   `s       .          0    31514    customer_tickets 
   TABLE DATA           .   COPY public.customer_tickets (id) FROM stdin;
    public          postgres    false    218   v       1          0    31564    tickets 
   TABLE DATA           p   COPY public.tickets (id, address, code_ticket, customer_name, customer_phone, num_tickets, trip_id) FROM stdin;
    public          postgres    false    221   6v       -          0    31417 	   time_trip 
   TABLE DATA           Q   COPY public.time_trip (id, end_day, end_time, start_day, start_time) FROM stdin;
    public          postgres    false    217   �w       2          0    31571    trips 
   TABLE DATA           C   COPY public.trips (id, name, timetrip_id, bus_type_id) FROM stdin;
    public          postgres    false    222   �x       <           0    0    time_trip_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.time_trip_id_seq', 18, true);
          public          postgres    false    216            4          0    0    BLOBS    BLOBS                             false   �{       �           2606    31151    bus_floors bus_floors_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.bus_floors
    ADD CONSTRAINT bus_floors_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.bus_floors DROP CONSTRAINT bus_floors_pkey;
       public            postgres    false    214            �           2606    31560    bus_slot bus_slot_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT bus_slot_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT bus_slot_pkey;
       public            postgres    false    219            �           2606    31225    bus_types bus_types_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.bus_types
    ADD CONSTRAINT bus_types_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.bus_types DROP CONSTRAINT bus_types_pkey;
       public            postgres    false    215            �           2606    31518 &   customer_tickets customer_tickets_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.customer_tickets
    ADD CONSTRAINT customer_tickets_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.customer_tickets DROP CONSTRAINT customer_tickets_pkey;
       public            postgres    false    218            �           2606    31570    tickets tickets_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.tickets DROP CONSTRAINT tickets_pkey;
       public            postgres    false    221            �           2606    31422    time_trip time_trip_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.time_trip
    ADD CONSTRAINT time_trip_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.time_trip DROP CONSTRAINT time_trip_pkey;
       public            postgres    false    217            �           2606    31575    trips trips_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.trips
    ADD CONSTRAINT trips_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.trips DROP CONSTRAINT trips_pkey;
       public            postgres    false    222            �           2606    31579 %   bus_slot uk_7uq20tnrfwwfl5ac9xy6i458o 
   CONSTRAINT     g   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT uk_7uq20tnrfwwfl5ac9xy6i458o UNIQUE (bus_type_id);
 O   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT uk_7uq20tnrfwwfl5ac9xy6i458o;
       public            postgres    false    219            �           2606    31577 %   bus_slot uk_en1yt16yfnmgmpg2b2opoc5wf 
   CONSTRAINT     h   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT uk_en1yt16yfnmgmpg2b2opoc5wf UNIQUE (bus_floor_id);
 O   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT uk_en1yt16yfnmgmpg2b2opoc5wf;
       public            postgres    false    219            �           2606    31595 +   customer_tickes fk1whl6hbx93ya2rqgi4oh4tm6x    FK CONSTRAINT     �   ALTER TABLE ONLY public.customer_tickes
    ADD CONSTRAINT fk1whl6hbx93ya2rqgi4oh4tm6x FOREIGN KEY (customer_id) REFERENCES public.tickets(id);
 U   ALTER TABLE ONLY public.customer_tickes DROP CONSTRAINT fk1whl6hbx93ya2rqgi4oh4tm6x;
       public          postgres    false    220    221    3218            �           2606    31610 !   trips fk3swq3aydmgckp9tdidd8164o7    FK CONSTRAINT     �   ALTER TABLE ONLY public.trips
    ADD CONSTRAINT fk3swq3aydmgckp9tdidd8164o7 FOREIGN KEY (bus_type_id) REFERENCES public.bus_types(id);
 K   ALTER TABLE ONLY public.trips DROP CONSTRAINT fk3swq3aydmgckp9tdidd8164o7;
       public          postgres    false    215    222    3206            �           2606    31600 #   tickets fkbcjlnu2low7r5vfimxextqab9    FK CONSTRAINT     �   ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fkbcjlnu2low7r5vfimxextqab9 FOREIGN KEY (trip_id) REFERENCES public.trips(id);
 M   ALTER TABLE ONLY public.tickets DROP CONSTRAINT fkbcjlnu2low7r5vfimxextqab9;
       public          postgres    false    222    3220    221            �           2606    31580 $   bus_slot fkfow5n7w9gm22gc7s0ean5yev7    FK CONSTRAINT     �   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT fkfow5n7w9gm22gc7s0ean5yev7 FOREIGN KEY (bus_floor_id) REFERENCES public.bus_floors(id);
 N   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT fkfow5n7w9gm22gc7s0ean5yev7;
       public          postgres    false    219    214    3204            �           2606    31585 $   bus_slot fkgvw52hylqmqg5cuul80ilvt7t    FK CONSTRAINT     �   ALTER TABLE ONLY public.bus_slot
    ADD CONSTRAINT fkgvw52hylqmqg5cuul80ilvt7t FOREIGN KEY (bus_type_id) REFERENCES public.bus_types(id);
 N   ALTER TABLE ONLY public.bus_slot DROP CONSTRAINT fkgvw52hylqmqg5cuul80ilvt7t;
       public          postgres    false    219    215    3206            �           2606    31590 +   customer_tickes fkhm2o1ob0g79gpvfou7iw4mxvh    FK CONSTRAINT     �   ALTER TABLE ONLY public.customer_tickes
    ADD CONSTRAINT fkhm2o1ob0g79gpvfou7iw4mxvh FOREIGN KEY (customer_seat_id) REFERENCES public.bus_slot(id);
 U   ALTER TABLE ONLY public.customer_tickes DROP CONSTRAINT fkhm2o1ob0g79gpvfou7iw4mxvh;
       public          postgres    false    219    3212    220            �           2606    31605 !   trips fks0qykyuj59nhfhy2773jl3isx    FK CONSTRAINT     �   ALTER TABLE ONLY public.trips
    ADD CONSTRAINT fks0qykyuj59nhfhy2773jl3isx FOREIGN KEY (timetrip_id) REFERENCES public.time_trip(id);
 K   ALTER TABLE ONLY public.trips DROP CONSTRAINT fks0qykyuj59nhfhy2773jl3isx;
       public          postgres    false    3208    222    217            *   m   x�-�1�0 �9��l�_��S�Hi��v�|w�I���l0J�B7b4�SdhI��N����<*1�ϥ�a�H$U���D��u��Z!��@�6QB�WC'f>W��m����i#�      /      x��}I�%9��:�.l�LbY~�>Ao�'��K�����##%�G��p{4� K������1�5k8����h*�?��?����d��aI�6��锐��3e�3�������k�9e�rK1Ԙw�kH�$�ζl��<B{95ˡ�[C5H���>8G_'���Ygm�����P׹a�QC���R962�V�P���;�����ڶce̸,Rhw	ms�9w���g.M����R4Q��]��Ω�|w�T|t�.D�*CRS
����Ύw�`I7�t� ��ǲ{ƕ��B������y/ʆ�������3���<ir�����l�������C�o�S����#թTcD�N�x�Xj���0D[���ϫJ.��:�w4��Y3��_��3���y�#�y��=5՘¾�:a���(WW�c�G�KYg�7���[�W9Ix%�Ƴk1(���\ںMD��B<��\��Y��T8۫�{.u��X�B�/N8��oX%[�M��Um���WI)^�����]zK8g�\�����˱���T|j��B�G�p 
g�g���9�εx֍/�*���^ >���eX�:�#ե���uK�C�Yr�W�Gfj��:��.�Jioݰ���m	�uF�B�$Y��X���� ��K�;/�p�z�t|�֯L��쓪��6G��_��c7�B�r��k�	�&���=��rO0�y*��Y�m���#�ϳz��t�Ē��U����cHw�Un/R�#��ˬ:#�ɱHX^ �ٍ�N|�� \6P��f͂uF����ԅ�4vמguE(O<pyxEǺ쵤��/Q��/f��� �!���8���n�Vs==��ЖW�	�V�Ska6<�O�:���1�!TcZp,���8 ܫ��MK�z4�1�!��Fhv޷1d�I��!���f�t��Bs�j!ވ��K�7�`yHނ�z޿C�m]�¥ LAWj��G���ȳ,H�s �$X$|�3�����@L@�y����#�j�V�,��E��8�,=F8Z;���ئ	��QWU�\@��f��� �b�9�]=�j�3���3+�{����Yau=o8�:�Z��O)���p*I�~��cg�-�׃�V|o𴩜�Hgz���h�]�h������j��ji�kR����CꊄQQ�_E�`�O��A'�:?|�Ӂ#�*�(����.	��1��G������(��*fREt*�����^�.ۊ����jh�MG 2��W�q7)��jqH-c�1�0����.���P�`ѥ��.�:$���0�&vGȘZ!6�v�H�H�="n�4���U��y�`��d�m�I�d�����"&*x>\�f|�CX�}�ӡ����/���Ta"�<R]��rL:X��A���(����A_��[����\�bp��|�%�Xw��
\���
� ��s����v�Nm�{N��l��g��"A�l`����4V9
D�Huٖ�:o�K����@��-`�l j��4�o</��Ϻ!��QA�������/��� 7��f=�S��
>��.����^��.�A�\,/�k,���7�Y�3�1}>�TD&0�lJ�b@���q )+(|�P�P ��b}���?�L���s���d)/qq�@>y�� ]�Y;ܟN��y��E�Y����{i�� �BF^|{zgY��X� � f�n/�3`"L]��%���V�� �	�T���8�}��c��@Q	B3����*����=�uyS���Y���Gl����;��`�*pޔ�C(B=`ˈϰ��\��yA��j�ˠ��*��>7[ޓ�BX��FD�Gd��/gq�j��C�g�����r���rי���P!fD���勷������i���!��vx4`��3"����ND��N)���󤷭Z*����T����(�������n�ˤ�ʔ`�M0�����}�!��qϳ�}5�	�;�_�R���(q����ţ M�٣�T��Z� �5^XT-��'�U�K6p�s∮J�������揳x�vx�
mB�g�	<ChrZ:�t�8��Fӭ�?Jڞm���t�p���qϳ�^�$�>�:�5G�^���q�Դ\d�L��FCT�	va=��N9?��y��8Ҽ��B�������]�lkL� ���'!�D�YA��8*����,/�}�	��	U���ә�bYW""W�q�ł��baS�2Pv��۶��_��]���p�V�r��Sgj�����<�XgqHլ@���"�lp|��`�h���S珳��+,���^p�p��S�0�V�Y�|���G�f��kņӎ����-�[��Y<��ޭ�{�f-8A�P�.0喖��n0�lk5е[x�Dv1�T������qAg��� D�D@�9xYtf�@[g��8��
F�8N��%~ �3K��x��,��z��2�X����=g�<h��q�)E�*�sxm � �]�ߎ#8�E�`ue!H����!~ωws��J�	�ēP�WFzQ`��.ޱ ݛ�L�c�de� "h�Λ�ap6|EK���#�~JǗ��	�~h�h�`�;>��j�v�'gf��`�����b��E������D�w�u�&�h��A ��輨�!4��rg�G�"2;��j�k{��:���{��n�mvԦ`ƻq�/�P��������\����p^��9ӳ���V�7D�y,U o����ե�p���ߞ�/^�M�AWu�<?���`��!>�6�±�8̳��>��
f��
��f�0'0�c	�~�J�P�Cj�=�]ڙ�z�u{Դzq>c��zN�F�kSd���.�	���N.�*��#�$A�3�� a���{�-֚}~�գX�����pr��N`.xO����P�G�ڡ&�9&?:Aქ�k��ʇ�]��K�����8Q�	&�3��wW���\&�i�� �Z�X�p��2��c�f�S��z���=��CQގ�Ct�R����ri]�Z=�������J+ �@����p��-�C�����S�:/�5��}	�
�\����z�u¥� `�^��0$��R�e�=+�����'u�rq����,@q�C���l��AU`�`.�
��ڰ$5�a�lk�Y�$� ��!#":�kױ��2ힷun,��t� ޖ���@Q�.���_��0&�Wa(̅VvBR,��s��:���
�����VvȀ(t@�[ѓ]��~S��w�%�,�<�Ź%����m��"6Y�X�!w	�u������˶L�T�0|%s�����t�UP���Z��dON�n����\���b�Wj5߿�7���2/X��̪���.`m��ZBm��hs�N�����=r+_�"{�Ε�5����a�U)Lh�I;��m�#,'�6�3�k¢������at��
�3���0��z��^�)S-�%��#��& lDj l�V��Tc(w�����ڋZBe�*5i�����D
5�Vi����V�ЌXow�)ˎ"�뺖�$�6�z�N�G(�DA����J ����kIOju�@�jx3����ڙoN
%y�Z�P��P���L�@ת���Ɛ{^س��!uH-y�RH�� ?��.���p�j����9�7
��J��X0_i�#�ˇZ=R�8P���;�b-{Fhd�P�P��Q;���33cU����n�����+IGTP��D�A����E�O\���W!1>5��3�n5��b�b�ZRW�,,$@�a��3�nW��A�Y��2���{m0zV�E���z����V��.-�ݨ�,}�5�n�<��x�)�Q>��yV�4Qz_�vX7�"Z��$�>����ʛ��t�����f�Ps�j�<�I+{�BUk��04�XxW����C���8ʳY(G`P/U@�<�"#���e[;�1�l�wL�f�
S ���m��P��Y�b���A*-�K�ѻT�+��<��V�TĥƨG}������sVb"8��z���'�e    k�SfY+@l�r�³�	d�m�1,(�'߮`�X��>�[ՙ]�uA�]�D�c�Vȭ0ApL/�(.ۊ�,��Z~�,|jP��J�����l+���KE ��yf2��,!V��*�W!�IFF�ܖ��RR�l��o)��2��q-
��`|�]��B�G({N�+ J0-���$���H���x��8����&p&��@�����ڋZBc�C��O� k�� )�1����B�U3P`����gʫ!ĚR�З�7�4�Еm�0΢�j`�
�6�Ú���V�P|G�p�p+Os�@Qa�!f[}�4%��z���m,�m���� au��_��:�0 FH�Xu�4�h����.�E��3�����C���:%dvf4���B�ˤJ��/�1/x�i���\��zl*�;kO���" �a�՜��h|��!��#u�Du	TVem�sk���z��)�,�Xn�h$��:N�Gm�r�����^>��m���iū�U8��(>�"Z���!0>���3�����2��Hg}�٠O����� �Р!k�գYm�j�V�N~�ɎQ���F�{ݔ\��r�3��m���6�d�-juH�/-a���<E�B,,��Z�C��s���#���R8���֛^�bi��z4���= �����K���
����b����C�yR��vdf�m@ȅ�B��-t��'��Ī���V`s`����j�� [�.;���^?��
�sD �pk��JZ�-@�%+�C��PP�����-c5�%u\@+޼��:���ĩ	���1p"ޖl�G���}u.۲���lJ���79n�X&-}��!��	f���' 6� ],Y��wY�	��6K��xl��A�
�v;�Z='�ʦ�D���������g�=^��6bæ��̠\�R��B��P���s�9��9��"�R��)1�x�.�����m�!������-,9i`�1}5M��y/P:m`�+$v�+]�R0��:�f`�����O�3�`����̯��!�����q��-���;H�!�ĳʋZB%Cm���o�o�=��,�"��Ww�=B�uv�@�	@�]�r��g�� -+��:�n陙 B�x��V �V��IV����
v�����v�R�@h��k���E��uB�Z�dK8B�7g�>��QT@�]#�&�;�m�a�Bg�)�C��S�����f��2)�&ܡ�"�u���*<R/ Н,�%�ni�WC��b��m>��|>E ./�ŕ�9�'��q
:���6��� ��`��<�m 	�h����T�󤮯2�?��,��q{�{��ʝw@����)�G�Na*�Z��]�b�H,q��>��|
��f}΂�>�����<�Pc[�#��Sr�(xv6�����Q�3���H���S��W$�i.�)���P�'��s�Q�q�8��Rao7+�c��M�+
�S#H�#��SX� ���[�g1 �n����$>��˧��V�Ѭ*��[s������(�ˤ6�T6H�<y�����UU��O�˦V�S�:� �o�,����@��)��˨n�bdP(�u`�� �E�ƺ֖�9���Y�F�xn���`�,�v��ٚ�=��UX���z��.9�v j�����bW�a�4{u�ɿ�~��UாJF��2S��X�����<�a��(	��a�H��Yj'�(�y)
�4�V�{������P���+~���t�(`�]��S��7�<B�o����={���:���+~�7���ZHT$����L�WW��*����)�3Mp�<�J��K�f���5�_�p���O�)�>����+Ѓ��t�Wzq��L��͛;x�B凞ff+ 
��lZ?��2�	.�e|wA
_��X�D�ۇSR�Mu1e{&'({����̏�R_�J.��K&�_�j~�˞+ȏ�����q@�mg���؀T;&����X�sy����������y�=��b������P� U�6�b2G���������>����7c�pLxR2цHxW��/N�<�A�;������lT(o��/N�(0h0]�;�jeF��M��v����י��C{8��3�$��x+%߷��˧�wg|y,q/@@vCff�\0�f/N�T�Q8)��>�~�cI��e
�<�\>E#�$�(��
3X�+�L�D��{�p�ǧ�Z�Z�|]����ʱ1�=�3��?��)���g�?똵0mu�^�R{���O�k;� 4Ɋ�4 ��g�QdS���`��ܣ2���Uƪ����/R������C��B˫tvI��65z� %BI��џ38�s��_mp�Ջ�'�F�d�ø"j�x�"+��|[�=B+�]{��w�"�TR�3��������w���=�JN벡����g�����8��)߂���u�՘q�=�ߚ��z;��2�u�ش&`O�R8v������]����V��V� JVg�dt��j�-�G���/X�Z,	�}"p�_�	���`��)��;��l`��=��QA�;ۏ�[�\&�V�߆�������0�`��P|8�!��f ��� [�F�s�!�Jr&�b�ծ9���ֺ��)��ޜÅh��M�����*+��b_v��a�| 
�p����	�����͈�L�s�b�� ��֨����]� ~U�*�H�9�е��������2�c0P� L��z����m�M���]��u��نr��
�	o8\�B2����n=�*qZM%Xd��bV1Nϻ�,�i���D��~G0uN��"Y�@ �Ey�~��<o+�Y�ʻ�VX
}Mp5�����]�Hۧ�T�[���	c�O{v��5��|���yvOJ��c@�
l��:�&����"��4�-���`��z�-�'��f��.nO�~Y`����(�-�'��f��0�\�E�_XK�@9�nR,U\����Fq�t��_�{��]�U�4c]سt�ofA�,����������E@�����.�P��˕��.HR�@/����_�+ f�;@�R��-�Cj�x|=m�"�RcWwg��Ȝ��u��m��W�����ؗ4q���������bi��CZ�堃`aB����v$��-l��r��0��LN�������#t��ǐ9;��3�.���,�g��[y����|YJd� /oV�jYV����r	-�������l,,�[�����p휍v�:���1�:;���j<�7��!��L*�Q���0ke��t�Pmg���#tk�ƨ"�Al=�Jd6{Y��G( DS��'�^� �JfV����:.BY�(ȱ��,�,���N5�c���!T5
�Ye6�� �AHl̺�R[�P����u&�%�`9� +�����:`����=�:%?,���u�#2��I,���.�b!(�`���J��1+�f�`[�>a׹�ə΁:K�������UF{Kr���kE� ��Qڅ�������pW�C��2s:�t(���s�;�\@�&g�|�ե�X��'�q��&0yĕ��VϹ�����b{^o��L��H�ZR�!��)C�[8V�x���#��j�<��;pP���e�, �RkY�����Hqor&93�"��!� n���:���rb��t� �}w��ҷ��z��}Ȭ+;�8�>�p�j�Q�e[���p3�V��wH���u?��.ے���U����!w��� A�i/�.����U# lN:�� ��:�=�����Wţ���OY�͞f6J��(����n㉯�CQ؀��h"f�E��.������98<�A^�3+@@,�t��WS���z��M[5����|������ �?������㦙:��Ln[��r-ۇZRoGl��Ux���}������t�o�+ܖ�O��) x��)ę{>�K�2p<��?�Ŧ��� s�U�ěl�=    x ����������.�4�]~�]B/h� V^���hz-�-yČ_��x�#�.�;�`% �&��o����ۭju�+ŗ C9q��|^�g������r�����h8�)���.��뀁����u\x�>�;��*rOe����0��p �]s�'�tYZ(�L�*����6vo�^��y�z��9y&󦉵s��=��j��iOI6�HlOE�f���C9����M�qH�4�d%�ʙ�10!�^�¥p*�Z=RAk�M�G�� ��t�,�����qU�{�H~Xl�Y�|����.l�?�ꐪKt�E�slf��l= I��0j~��#5���ڡT��n6С�rG �j�Hmy��A��a(���0|1o�����RS�=r.g]4,�\���R��x+��Z=R�
�4�99/r�!�5�w����oO���IO��D��	<9���y�����0�l��K�H</�/Ga hsuþ����M�.�RPT�	��CxX{
/ȑ����l��Z=~ �k��}�q�f�;6~k��y��m��Á>�x��X�������l;gN(���\+���h%{�[����˶f�p��r}�N��O7&��Q4�Z=��̸*qJ)_�r�d�NhĴ�C�O8����E㜯;�1������.�bmTf�8���Vg��sn����r��	{�U4��!t�!�ԫ1��{����^�f�m�*��b�ҙJ{xzo�q4���e��e[〾r'�(�B`�[~��c�pگ-����@}�m���87	����+�����%���yV�f��3�:�E�}��S��L'��s�+q�>mM����7��jU�SUo3G��ˁUL'����&�z����VY W�A�-N{���
x�֪`'�!�����Ṥ�\�yO+�LF�P �kR-�`��%�s�g���\��_������ J�O�QScFDx=4���/�'�r�.��/���-�h�g��n���8Y�E޾�����.��No��t���Q雞�U�K<��/��|������/o�hk>��8݆�S�/�juHfRO���ބ����e0! 1~�������_`����4&G~�n�z��7w�\FUR5y���1�;0���S{���ۮ�ځ�cj@؍�5q���d#ޙ-����eV[�`j� V���Ӂz�9�q��b�eW�ج��X#�� �s����r����V�q#�?$�6�0�qφ�޽^ޞ\�U���$�����L�Y�X��$�|뺙�28��]'����z�o&��
�	[�fd���p-G������UN�P��\��
�O8��dX������pM�O�s�*�J�g3_*%68q��P�Cjk�~�0�u�̑/�?����b��_g�m�3���3�������}�4�z�˶w8�!��G22ɘ8D��@B�B��.�v�Jɗ;��{;����C��gM ,�6=!�E��9�(j��Y�KvٖJm9�\PBv_Lj��rƬ���7I�!5-�⃛Ua�Pa��j!Mv�E�K�m?�H�4�岹���q0 j����o���`\@R=�	���e�Y�%��z6��_���l_|"�a�����-���'��r���<��s���<l1�pw@=S�
m�Ӹ8Պ��l@��.��qG��k�w�[Z��+��}r-7(�{�8U���˵���[�f7��,T`W;��Y'7΀�_��Ch�ǖ�)ߧ=-��ݗ��c!�I��4��ZtM�m	1��h��p�j��D����Z�]hK`Ó;��p��0����>�b�/���B���9+�	��G>�m8�-�-}��p���c�p��w�R�8����qڵ�U8�.���tJ�7Ä`7�	���N�P;ոRI�� ��
4�-� Xy�;;R%��q����"Y�N����:�rS�� �0Qfp�$��2�r�ɂ�j���ukk,,v5�32.��9!g}�/���R���V��p�Z�m�8��`��7�b��Z�]jMu���V��:�.��l�44��t�D��5}s1�2����U�#n���u y���I\�yo�4�W-~t���_�G(��&P;�0o�g`%�����S��ǥ ���V,\V��֔���D�2Z����� g�����w�$�Şis�����������phnU�6.�ܱÐד��z�wh���4�&�Ⱥ���(�,��uy�R6 >�����g�++��w��������n�C���+V�0T �8��J�:�=g�<͍�����ޑ:�7[�(_��C�y�B��9ձ������s<�A�*�?>�� :�P�E��ʘmx�������j\� ��Xkɪ���JܮN�}�4�W���,���d'+Y��HX8����*�6�Bm�L�L�,a��x@[��Y�.��O�ն�M
K9�e'5nC���'ಫ~�(g�l��je+�ʘu�x__��4A��x7-���V/G���5q'/i�=�� Iþv#~`m��U�]h���,���R�.:�^�a�~u���З�P��g�=pb w��)�`ko��x��[�q������DQ���W#�]Z���M{��� :y�C���5��T�_��C(|�^���~V�w�adyi����U�:�*�~gz�$�얜7� [Yp��o�C�.)��1���R{]d�*5�����a��O�����&U9�_��ȵ��0W����](�YI��OLW8h2PL�A+�Rܯ��eR��[RUPN�`jq�������B��wH� ����Q�~�$p'��U2%w��_��Ǩ��.���Ǩ��Flt �1��t��V�w�2�:\"�X�P���g�: ��t��ݢ�eV�qB�Ff1YmTx�� ���K?��� �;`q���>57��0���j�h��l� �F����c��$Ī����.�!U'�⣕�B2/XbNQ���;g���! \��GY�B�J��b�1��m��2��7�8Xnmϕ�n)�8�/_\�Hzw_e�m��)[�O��Ve�F0 Ӟ���-����$*Av]��7D�1z�8�_��c[-����u�rx�4�}Uf��|ݿ�'��d���+��g��� 
d��1����e[
l���LX��	��)��ѱw�[���@0-�	3K��昅6��:�=ogZ��!՞��g��f}_	g3���]�g=�:�ƾ���U���y����청���kƭz,�h9��C� �|FbOe���ZS�����3�r�6�~��gጇSy��1�W�RĆ�w��K5+O&�k9�Y9�"1�Z��o���%R��[�F�:�	�S����ܾv��:�R�P*@���:7x{Y&#k���^p֯C��v�J�0\]���g�,��h�����<+����n�#����L�^pͿ]������ֳ���N������ۺ���R�(�'�@�3��M_BU�Ɵ�E�Գ���q����8�:0�֓{���dqibH%˰ ˹c�I��>_�˶"��6ĕ�d:'�5���m�x���!u�y��]tc�(W`�g&՘7��o gv�V������;�Zڻ��hΎ�o�\��V>@~�K<�jxV=x[�
�& XϿ�3�	h�mNv$szh|�Vvଵ�����!u��P@��7��o�2bk@tZ���u�r�cS�5]��u҄�g�m���"�Gj6x%�\�M��ȶ�Сe�"�C�(x7�_p�;�ij�m�!�d�w��9�D�����a�͑34>���k[�e[�eȄ��=I�D��8�xɯ��s\�˹I��ç����G�	x�-/.�:���l��av�q�qLK�w�C�϶vz�D���_�u6�d7�����϶dƽ��
�@��[i�8B�KD8|��e[���f�qR�V-�}/p��;�mU��`�{X}&Vó�R:��k~���m�I� H�űNfB���`j32�#i� A�m�K��7�Hd��y��B	��L��)q ��1�4�RI"�!3�im�8b�P�����jLJ�F�K��	�T h  /�نg��#�p� w��g>�`�(P\�@{��E:������}�{�;��������>g�C�^�-.�a�+ז���5��5���ҩ)pu������̼�=�4}q\g^ٳQ��4�Ŋ��8�
� ��*���+"�F�P��>��v�c�������Ool-.���k�)=B�Q*� �MbٯϷ�b <r)
�2�ݘ�sn)�Yw�����J�q����f	�����Wl����y'~W_�,�,p��4P�)Ax�8ϻ]��b�-� *��o_�ĵ5ܒa�-e�.cE���է�W��p� �Q7M껭���U�tA��3J9q��%��	A�A�����+w.�
R���j�l��#����{.{=�����I2��*�"pb���K�.��g��X%6�<�|&�^. �����t�*�o�L����q�<�e�G{kd�˶���	���U���L��_#���沭d��~�2�:�q��qj�m�)�[��\�u�F&w�Jf7�3�dۅ���|b\�	�l�r_�/w6�b��a8��lY�9����\�դV�iQ��`��k��t��"�沭�/ʦ�0+�	��'��%�̆ˎ�O���l��	��܅���U3�e�&{���'�m��k�� �2��V,6ꟽ���T�m�����m=�ihxQKע�!z�]���e[Y��Bj���u��2�����e�ܥ�l���j�z���w�h�����[������Dq�#T(������])�]�e@���S-&��o?���7���[�X�� ��3T�7ܠ�#�o����ӏ�>�S.aτ��D�T�F�w�L��̚��U�A�`}x�uA��)Q���	�f���A��pBN>�����j����]�Ӹ�(��<ex�s�z��
�w�.�jy9�d0�R�W��"-Y���{{5�e[�VV�9���ensʴ���W��Y�3����//p؜���3T�Ӛ�\o05�r�Q�.��T�=�8]�(�`�K8d l��:\�5�2�_(��٩1hC�X��m��u�lKTZ�6�S�K�D[)��i_^�ˉ��&)��Sѓ=}5�}��w&,"�5	+�Ćk�ٸ�k%�9�)�Ͻ�����˓�d?�=+�+��M��nϞM׍��k�\�Z�'�-ۊq��������Rh�^��Ed,r������dZi<9q&2GW��oc�[1��AR&-?>�����b���km��vx�F�V/�3���͇u��V��%��Rϓ����F��hS;x�2N�曤����B��U��r���wY�_(��+ D�3�H��;��M7�R 5���_u�C(޴4n���+��!	⺲ʅ�V�y+f<g�|
z?v!#䴦��RQ��]��(�Pa�3A�~�a��j�4З��H<BW��L��q���k�ۺi��3~�KRUF����t}�ƚd!#�Y����{ԟ����3%���|nl��e�k�L.���_�sm�W�C_�X���y'�$�U�b�pڞ�s�8�\ ��������L�l�`�T��^`ݛӠ�����}v�U��}|��ٕ>؄5��2�~����W�Z�;��S!,YSo��}��R�(��pq;�6�Sq}�́�؆������
9�E�'�N砶�R���g#��Z5�r�9e��`]�}�<�/=M\Zu'��\ c ܈G$������E��R*N6	�Y(�Y�G�'��k9���	������fV7
|5�P/5��D�?.�ZK���M��.t@D��r�Rq�����ձtd�ў�>��Ez��4�Z�����4�v���kka�,h�-�v�Tr���%�*T��5?#��3�p�����Ռ{��v`r�댜��N� P+��i'|���O�䆍��s��s#�/�7�A._͸'���XG--���x��v�['��|5���s�9�$�?"���sV��~�j�_͸�Ɩ�J�>s︷Q4cU�[���f��
�)�x�hu�
o�\�ԟ�	���wT<���XP�a�JH(�t�����xZ�%]�"����⽰����l �[��Vk��2���z:xJ4V����!��.- ����܅c�㲶"׮���!t�<��y3��Ԙ�^t���uM:�����`��F���bg�.���}q������0VNB�L�� <M1�5������;�����F�
��p�:�=�U|�>BG*��Ң���؈��/�q�V{�k���Y��䓟���a���WV^@��$q�C�ر�|3k�+�2��T�1kf�tzS��R����6��c!��N�!s[x�|���ˤ�Ai���IHU��^�X����ۮ��]j6 ��]H�k�x���MoY���N�ߥ�my�Z�m�bT�47q�[��Y0$�:AǹN9c��Mk��"�`^����_�eV�8X;s�p~��r��_ڕM�p3��D�ؕ��r��!47w�̑�~@��N�cX�ufk�ӌh�08���������~�˲f��dc*�<-����ZRh�
����: 5Y0o�,�q�J��?�Η�H.۪�iݕ�S�	�Nuu����+��_��C�����s�x�Z�-�c`�n��	:����:�ЊpcA�n�k�i��s�R���.�pkٳ�N��$�;� \���	:��p�ʒ~�9s��d����w��g�7����2�J�T�3Us={��;�����JI�m1�PxqcY_KZ��jǖ��oևǶ�T��/�'���o[�
�5�q6�N��MS��ghēc_w<kaăC*�N�a[휽�-�6+х��m�x��u�S߲˶L+�����@n��\�.-@����,k�k!�b�69�xK.�b;��W'��Y�\����X&��l F/0��4�tH]���j�̂�^���ȸr�n���Z\�P�{��8��/� ����]үN�c�h,B/����g3t,q�yII�a�˶ȹ�N�6�[W��ؽ�ƲUfo��?T\�%#��0�{,��%Ɲ8�����(�W'��R�\�ݠ��%�O���Z�|m��ٿ:A�׆��qr��!��|h�(jĄ�W�x֞r��j�V �U���f�p9��W�g�[8��[�X���P�AӴ����_���r	�!��);�m���/Ｃ��˶�ئ��W�G^���]�p���"P��_9���c�0/̻�4|�~)\d��ɪ�e[8��~��G%ʀ�ZO�0@g1�v���_yXa��R��©���:B�W[�<��W?�&&'�%ƭ�<���$���=�e[GN+q��+��p��i7����W�0+r�:��(U���U�բm���r�+sX�k��"��j��qJM;�#��l���������Q	PR      +     x��V�n�V]S_1�K���H.mŵ��Nk�F[ts�	��*���]AQ�E�b��@�&��������t��c�)���c�3/���/4�s�0��xL�Z�4��\����ǥA�tH��ܪ˻p��i]>�!��œ�E:�|Z��ґ'\����.�<\�-�.��qC��w)L��%xhx]�I�^���(����-�Vs/��.݌���N��GleI ��I1�I�d��0U�C녎)���3��:3��2a9��ݷ���HGH���D8���0ԋ�Ў��;HGuy?Od�!���}� �<��Xc:eգ����C�"��f��=B�h�e��L����3 .Y}�y�0')��|*:t��O�sT���[���
9x?�����`l]�6�� 6�i�C������r9��L�y��V�61C�c
FZ��(L���|�[}4�xZ@�~_I�z]��@�o���ù�c�K8�Z�S�nJf���+�:��A�
0-c������X܋/�ýt�H�#�ܜf���c�T��֙�k
W{Nh;n(T���c�n�]���Ծe�oJ��Ȯ2|��8�������LE��xߏ*��/�i�iu���n�ͣ3	+�4T�]ty	#�,�"���%�Y#Q{7_���R)�ȱ�3�k��{�7`�]Ai�g�<l�3�Ӱ1�!g��7C��}�tu����hjO�\�I
MX��SðX��^p.�K��m�W���Q�V�x �7T�H�Y�v���9��Z^!蒨P=K�����m�%h��3�Pܩr(0�J��^��N? �8�^�;�үh��l;�^�qi����-��}���M�����z=i��Ӕ��e�I�7������$�߅a0��lY}`d��S�
7�N-aj��MW��s��|C�<�k��%����D�^m��|C��\������:�J��<;��`y. ���,%�R�\i���7��[�Z~/���h�;�d����#q�@��?N�"�biq�*�� �RٲT�a):��竎m��j��܄�z̴4G�4q�K7q�t�W�Z���p����f(c	���3J\�0#��ɰC�/jN];�V�zhf'>	��V�q&�@$����&��E��-��]�!8��<֒��ګ~K`cj�B$CN��s���]��?+|���"�d�
���Z�b�Z�C���L,B+���E0��oUoj�Q�V�Q�]n͊ԧ5�~A��s�"��Q)a������D�F�O��;k��=ܹq�?�݀-L�u���(��b8���^��7�]      0   �  x��T˱�0[����`>���?�	O� �d&�dI�۶
�����h�QZuc�U14~���κTٙ��ɽ9�f8�k��ӯͻ�	oGQO�Q�,���γ��O=�FG;$�$��I�j5�f�zl�L�Cm�"�ōnv]�:毯{�t#=���lZ����d��oHC�

ihW����&�]���~��;�q���I؃�h���6�r�DrYަҭV$��|�^�n�|�~C�^5�J낧���.b� �N��g���_�`�h����2nz�&�-i�C��Bx�ĉݹ}C�=�=@b��@4K�h��XVD�����w;�AqQ�\�2�ѿ!�W�s�I"��S-j���޼-��t�t�~ �5^����E)nP�2���:�����h9���4�+�ܽ)�%��4
�'>C�ύ�,�8r�n��/�#�I�M�kM��D  8۩�z*�k�:�I�oH�j�/|j/�\qFd�Z�wt��$��&� �X�}g�'\��R^�R��@�:�E8�ed�*�2�!��P�7��n�w&q�f!!���M_�_�NI#_4�m�>Β����b^���L��7O��7S$�R꨽���|�,�C�tu��|C�̼��5�;f �.:P5h���ǅᶓ������uUJ[��� �ߐ��n���E��A�c�r*N[Ͽ��y�>��l      .      x������ � �      1   �  x��ӿ�A�x�)�J���tW��%F&��է�#��"�o`"fF���ދ�ػ�(��F�P�o���j�ƀ&j��V�қU)ݢ�tq�}\\�.�mO��#�@�4=�z��{��������S рD�&�p�H�E�d#@�C�F�Źe���4cKҪggN�P�H^�'UYk=����G�$�sZ!�����:h�Z�啂�U(�(�n�1�f�(�؂A^��g⚌�6��HG�c�t��p�y[߼ۖ�h�e�Fbєm0������YG����;Gn�u���W���$B%w��sLjJg��T����_�N��
���6+	EP �դ���-¨�H#+�� S�1���t�\r��>>���r��$̽y.���a/�`Z@rN1�e������my�#�{�-7�߶�_"�RN��H��(w�<�7���;	�      -   �   x�}���0�T/��.���_G$˺� z��	�,�0A/��Fơ0#��L�L��u���B�b>��.S����nVM��=��==q5C���L���ӓW�$~62g���L��ho0�(�q �K��aP��H몑8VUG,�즺Kܭ�C��D%=*��u�m�\��j�|��T5��_T45Nx�51f�c�w�      2   �  x�����E��٧�(�����*�N��8�T�)v��eqH���p�NdH���5�oB��,�ǡ��F-ͨ���W�E5�60J�b�q D�4X�Z��N����������a����q�~�����_�b�U&��� Z��15�Re�ciQ0Ci�7Q`��,�(�(�]s���8�7o�K:qh��(�w�"�ޤ�;�:�����Dgt���(X50��$#oc��جG��H+A�@Й3����_F��ju��o����
͒)�G�|*Cw;���>��ؗH3
[O��������
5�3�w�Y[�"�t���������m�Uw�&��M6���XO�65Y��c����g�@�P#����6�`�+�^�O��/o�w}�m�̦Y���\�4$�a�6j2$����~}��K�mc�V�)�&mx�趤��ȕ.���W����vB�Ъ2t�y悎p����_�߮7��ۭ/U]�-��.�$	���^C^��M6���R՛�ا���N��C2��,7���/�?�����֗�K3��-����/��L�Z̍�F]����V�2/���F�%�;�Q*��H�3�"�:�*X�>'��x�L��$ �⛆�%��()��.��0Ca�Z�GD{�kՔ���Xj�5�D�
y�� �P1��b�j���j���wK�E�j�9Ì)\ĉ ����7�%��7�v��_���      4   z   -   x�" ��Máy lạnh, giường, quá tốt)�4          