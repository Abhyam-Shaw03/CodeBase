#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct NODU
{
    int sem;
    int marks;
    struct NODU *next;
}nodule;
typedef struct NODA
{
    float aver;
    struct NODA *next;
}nodulous;
typedef struct NODE
{
    int roll;
    char name[15];
    struct NODU *header;
    struct NODA *start;
    struct NODE *next,*prev;
}node;
node *head=NULL,*head1=NULL,*p,*p1,*temp=NULL,*temp1=NULL;
nodule *q,*q1;
nodulous *r;
int s;

void enroll()
{
    int roll;
    char name[15];
    p=(node*)malloc(sizeof(node));
    p1=(node*)malloc(sizeof(node));
    printf("Enter the roll number:");
    scanf("%d",&p->roll);
    p1->roll=p->roll;
    printf("\nEnter the name:");
    scanf("%s",&p->name);
    strcpy(p1->name,p->name);
    p->header=NULL;p->next=NULL;p->start=NULL;p->prev=NULL;
    p1->header=NULL;p1->next=NULL;p1->start=NULL;p1->prev=NULL;
    if(head==NULL && head1==NULL)
        {
            head=temp=p;
            head1=temp1=p1;
        }
    else
    {
        temp->next=p;temp1->next=p1;
        p->prev=temp;p1->prev=temp1;
        temp=p;temp1=p1;
    }
}

void assignment()
{
    int roll,t=0;
    node *j,*k;
    nodule *i,*g;
    printf("Enter the roll whose marks you need to assign:");
    scanf("%d",&roll);
    if(head==NULL && head1==NULL)
    {
        printf("Link list is empty..!!!\n");
        return;
    }
    else
    {
        for(j=head;j!=NULL;j=j->next)
        {
            if(j->roll==roll)
            {
                for(k=head1;k!=NULL;k=k->next)
                {
                    if(k->roll==roll)
                        break;
                }
                t=1;
                q=(nodule*)malloc(sizeof(nodule));
                q1=(nodule*)malloc(sizeof(nodule));
                printf("\nEnter the semester number:");
                scanf("%d",&q->sem);
                q1->sem=q->sem;
                printf("\nEnter the marks scored:");
                scanf("%d",&q->marks);
                q1->marks=q->marks;
                q->next=NULL;q1->next=NULL;
                if(j->header==NULL && k->header==NULL)
                    {
                        j->header=q;k->header=q1;
                    }
                else
                {
                    i=j->header;g=k->header;
                    while(i->next!=NULL && g->next!=NULL)
                    {
                        i=i->next;
                        g=g->next;
                    }
                    i->next=q;g->next=q1;
                }
            }
        }
        if(t==0)
            printf("The roll number entered is not valid...!!\nPlease enter the correct roll number.\n");
        return;
    }
}

void display()
{
    node *k;
    nodule *g;
    int countt=0;
    if(head1==NULL)
    {
        printf("Link list is empty..!!!\n");
        return;
    }
    else
    {
        printf("SL.NO.     ROLL      NAME     ");
        for(int z=1;z<=s;z++)
        {
            printf("   sem%d  ",z);
        }
        for(k=head1;k!=NULL;k=k->next)
            {
                countt++;
                printf("\n  %d         %d       %s  ",countt,k->roll,k->name);
                for(g=k->header;g!=NULL;g=g->next)
                    printf("   %d    ",g->marks);
            }
    }
}

void average()
{
    char nm[15];int c=0,m;
    node *j,*n;
    nodule *i;
    nodulous *k;
    int countt=0;
    if(head==NULL)
    {
        printf("Link list is empty..!!!\n");
        return;
    }
    else
    {
        for(j=head;j!=NULL;j=j->next)
        {
            c++;
            int n=0,total=0;
            float avg=0;
            for(i=j->header;i!=NULL;i=i->next)
            {
                n++;
                total=total+i->marks;
            }
            avg=(float)total/n;
            r=(nodulous*)malloc(sizeof(nodulous));
            r->aver=avg;
            r->next=NULL;
            if(j->start==NULL)
                j->start=r;
        }
        for(j=head;j!=NULL;j=j->next)
        {
            for(n=j->next;n!=NULL;n=n->next)
            {
                if(n->start->aver>j->start->aver)
                {
                    float alt=j->start->aver;
                    j->start->aver=n->start->aver;
                    n->start->aver=alt;
                    int rl=j->roll;
                    j->roll=n->roll;
                    n->roll=rl;
                    strcpy(nm,j->name);
                    strcpy(j->name,n->name);
                    strcpy(n->name,nm);
                    nodule *d=j->header;
                    j->header=n->header;
                    n->header=d;
                }
            }
        }
        printf("SL.NO.     ROLL      NAME       AVERAGE");
        for(j=head;j!=NULL;j=j->next)
            {
                countt++;
                printf("\n  %d         %d       %s  ",countt,j->roll,j->name);
                for(k=j->start;k!=NULL;k=k->next)
                    printf("   %4f    ",k->aver);
            }
    }
    return;
}

void delete()
{
    int rl,t=0;
    node *j,*k;
    printf("Enter the roll number you want to remove: ");
    scanf("%d",&rl);
    if(head==NULL && head1==NULL)
    {
        printf("\nList is empty...!!");
        return;
    }
    else if(head!=NULL && head1!=NULL)
    {
        for(j=head;j!=NULL;j=j->next)
        {
            if(rl==j->roll)
            {
                for(k=head1;k!=NULL;k=k->next)
                    {
                        if(rl==k->roll)
                            break;
                    }
                t=1;
                if(j->next!=NULL && j->prev!=NULL)
                {
                    j->next->prev=j->prev;
                    j->prev->next=j->next;
                }
                else if(j->next==NULL && j->prev!=NULL)
                {
                    temp=j->prev;
                    temp->next=NULL;
                }
                else if(j->prev==NULL && j->next!=NULL)
                {
                    head=j->next;
                    head->prev=NULL;
                }
                else if(j->prev==NULL && j->next==NULL)
                    head=NULL;
                if(k->next!=NULL && k->prev!=NULL)
                {
                    k->next->prev=k->prev;
                    k->prev->next=k->next;
                }
                else if(k->next==NULL && k->prev!=NULL)
                {
                    temp1=k->prev;
                    temp1->next=NULL;
                }
                else if(k->prev==NULL && k->next!=NULL)
                {
                    head1=k->next;
                    head1->prev=NULL;
                }
                else if(k->prev==NULL && k->next==NULL)
                    head1=NULL;
                free(j->header);free(k->header);
                free(j->start);free(k->start);
                free(j);free(k);
            }
        }
    }
    if(t==0)
        printf("The roll number entered is not valid...!!\nPlease enter the correct roll number.\n");
}

void grade()
{
    node *j;
    printf("The students with A grade marks are:\n");
    for(j=head;j!=NULL;j=j->next)
    {
        if(j->start->aver>=75)
            printf("\n%s",j->name);
    }
    printf("\n\nThe students with B grade marks are:\n");
    for(j=head;j!=NULL;j=j->next)
    {
        if(j->start->aver<75 && j->start->aver>=45)
            printf("\n%s",j->name);
    }
    printf("\n\nThe students with C grade marks are:\n");
    for(j=head;j!=NULL;j=j->next)
    {
        if(j->start->aver<45)
            printf("\n%s",j->name);
    }
}

void main()
{
    int op;
    printf("The project is done by:\n");
    printf("COLLEGE: ASANSOL ENGINEERING COLLEGE\n");
    printf("DEPARTMENT: ARTIFICIAL INTELLIGENCE AND MACHINE LEARNING\n");
    printf("NAME: ABHYAM SHAW (10830621016)\n");
    printf("NAME: ANUPURBA KHAN (10830621026)\n");
    printf("\n\nEnter the number of semesters in the course: ");
    scanf("%d",&s);
    while(1)
    {
        printf("\n\n 1.ENROLL\n 2.DISPLAY\n 3.MARKS ASSIGN\n 4.AVERAGE\n 5.DELETE\n 6.GRADE\n 7.EXIT");
        printf("\nEnter your choice: ");
        scanf("%d",&op);
        switch(op)
        {
            case 1:
                enroll();
                break;
            case 2:
                display();
                break;
            case 3:
                assignment();
                break;
            case 4:
                average();
                break;
            case 5:
                delete();
                break;
            case 6:
                grade();
                break;
            case 7:
                exit(0);
            default:
                printf("\nChoose the right option..!!\n");
        }
    }
}
